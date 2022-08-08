package com.example.extend1.ui.admin.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.extend1.R
import com.example.extend1.databinding.FragmentRegisterAdminBinding
import com.example.extend1.model.User
import com.example.extend1.utils.error
import com.example.extend1.utils.gone
import com.example.extend1.utils.showToast
import com.example.extend1.utils.visible
import com.google.firebase.auth.FirebaseAuth


class RegisterAdminFragment : Fragment() {

    private val args: RegisterAdminFragmentArgs by navArgs()
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentRegisterAdminBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterAdminBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (args.role == getString(R.string.admin))
            binding.etNpwpAdminRegister.visible()
        else
            binding.etNpwpAdminRegister.gone()


        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnRegister.setOnClickListener {
                val nama = etNamaAdminRegister.text.toString()
                val npwp = etNpwpAdminRegister.text.toString()
                val email = etEmailAdminRegister.text.toString()
                val password = etPasswordAdminRegister.text.toString()

                if (nama.isEmpty()) {
                    etNamaAdminRegister.error("Name Tidak Boleh Kosong")
                } else if (args.role == getString(R.string.admin)) {
                    if (npwp.isEmpty()) {
                        etNpwpAdminRegister.error("NPWP tidak bisa kosong")
                    }
                } else if (email.isEmpty()) {
                    etEmailAdminRegister.error("Email tidak bisa kosong")
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailAdminRegister.error("Email tidak sesuai")
                } else if (password.isEmpty()) {
                    etPasswordAdminRegister.error("Password tidak bisa kosong")
                } else {
                    val user = User(
                        email = email,
                        password = password,
                        name = nama
                    )
                    // Save user to realtime database
                    registerViewModel.saveUser(user).observe(viewLifecycleOwner) {
                        if (it == true) {
                            registerFirebase(email, password)
                        } else {
                            requireContext().showToast("Registrasi User Gagal")
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
                    requireContext().showToast("Registrasi User Berhasil")
                    val action = RegisterAdminFragmentDirections.actionRegisterAdminFragmentToLoginAdminFragment(args.role)
                    findNavController().navigate(action)
                }
            }
            .addOnFailureListener {
                requireContext().showToast(it.message.toString())
            }
    }
}