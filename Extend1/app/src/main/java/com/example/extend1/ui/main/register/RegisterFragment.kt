package com.example.extend1.ui.main.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.extend1.R
import com.example.extend1.databinding.FragmentRegisterBinding
import com.example.extend1.model.Admin
import com.example.extend1.model.User
import com.example.extend1.utils.error
import com.example.extend1.utils.gone
import com.example.extend1.utils.showToast
import com.example.extend1.utils.visible
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private val args: RegisterFragmentArgs by navArgs()
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        if (args.role == getString(R.string.user))
            binding.tvRegistrationUserTittle.visible()
        else
            binding.tvRegistrationAdminTittle.visible()

        if (args.role == getString(R.string.admin))
            binding.etNpwpAdminRegister.visible()
        else
            binding.etNpwpAdminRegister.gone()


        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnRegister.setOnClickListener {
                val name = etNamaRegister.text.toString()
                val npwp = etNpwpAdminRegister.text.toString()
                val email = etEmailRegister.text.toString()
                val password = etPasswordRegister.text.toString()

                if (name.isEmpty()) {
                    etNamaRegister.error("Name Tidak Boleh Kosong")
                } else if (args.role == getString(R.string.admin) && npwp.isEmpty()) {
                    etNpwpAdminRegister.error("NPWP tidak bisa kosong")
                } else if (email.isEmpty()) {
                    etEmailRegister.error("Email tidak bisa kosong")
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailRegister.error("Email tidak sesuai")
                } else if (password.isEmpty()) {
                    etPasswordRegister.error("Password tidak bisa kosong")
                } else {
                    btnRegister.gone()
                    progressBar.visible()
                    if (args.role == getString(R.string.user)) {
                        val user = User(
                            email = email,
                            password = password,
                            name = name
                        )
                        // Save user to realtime database
                        registerViewModel.saveUser(user).observe(viewLifecycleOwner) {
                            if (it == true) {
                                registerFirebase(email, password)
                            } else {
                                btnRegister.visible()
                                progressBar.gone()
                                requireContext().showToast("Registrasi User Gagal")
                            }
                        }
                    } else {
                        val admin = Admin(
                            name = name,
                            npwp = npwp,
                            email = email,
                            password = password
                        )
                        registerViewModel.saveAdmin(admin).observe(viewLifecycleOwner) {
                            if (it == true) {
                                registerFirebase(email, password)
                            } else {
                                btnRegister.visible()
                                progressBar.gone()
                                requireContext().showToast("Registrasi Admin Gagal")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    requireContext().showToast("Registrasi Berhasil")
                    val action =
                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                            args.role
                        )
                    findNavController().navigate(action)
                }
            }
            .addOnFailureListener {
                binding.btnRegister.visible()
                binding.progressBar.gone()
                requireContext().showToast(it.message.toString())
            }
    }
}