package com.example.extend1.ui.main.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.extend1.R
import com.example.extend1.databinding.FragmentRegisterBinding
import com.example.extend1.model.Admin
import com.example.extend1.model.Company
import com.example.extend1.model.Student
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
        val dropdownPerusahaan = resources.getStringArray(R.array.Perusahaan)
        val dropdownAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, dropdownPerusahaan)
        binding.autoCompleteTextView.setAdapter(dropdownAdapter)

        auth = FirebaseAuth.getInstance()
        when (args.role) {
            getString(R.string.company) -> {
                binding.tvRegistrationCompanyTittle.visible()
            }
            getString(R.string.admin) -> {
                binding.tvRegistrationAdminTittle.visible()
                binding.etNpwpCompanyRegister.gone()
            }
            else -> {
                binding.tvRegistrationStudetTittle.visible()
                binding.etNpwpCompanyRegister.gone()
                binding.textInputLayout.visible()
            }
        }

        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnRegister.setOnClickListener {
                val name = etNamaRegister.text.toString()
                val npwp = etNpwpCompanyRegister.text.toString()
                val email = etEmailRegister.text.toString()
                val password = etPasswordRegister.text.toString()

                if (name.isEmpty()) {
                    etNamaRegister.error("Name Tidak Boleh Kosong")
                } else if (args.role == getString(R.string.company) && npwp.isEmpty()) {
                    etNpwpCompanyRegister.error("NPWP tidak bisa kosong")
                } else if (email.isEmpty()) {
                    etEmailRegister.error("Email tidak bisa kosong")
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailRegister.error("Email tidak sesuai")
                } else if (password.isEmpty()) {
                    etPasswordRegister.error("Password tidak bisa kosong")
                } else {
                    btnRegister.gone()
                    progressBar.visible()
                    if (args.role == getString(R.string.admin)) {
                        val admin = Admin(
                            email = email,
                            password = password,
                            name = name
                        )
                        // Save user to realtime database
                        registerViewModel.saveAdmin(admin).observe(viewLifecycleOwner) {
                            if (it == true) {
                                requireContext().showToast("Registrasi Berhasil")
                                val action =
                                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                                        args.role
                                    )
                                findNavController().navigate(action)
                            } else {
                                btnRegister.visible()
                                progressBar.gone()
                                requireContext().showToast("Registrasi Gagal")
                            }
                        }
                    } else if (args.role == getString(R.string.company)) {
                        val company = Company(
                            name = name,
                            npwp = npwp,
                            email = email,
                            password = password
                        )
                        registerViewModel.saveCompany(company).observe(viewLifecycleOwner) {
                            if (it == true) {
                                requireContext().showToast("Registrasi Berhasil")
                                val action =
                                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                                        args.role
                                    )
                                findNavController().navigate(action)
                            } else {
                                btnRegister.visible()
                                progressBar.gone()
                                requireContext().showToast("Registrasi Gagal")
                            }
                        }
                    } else {
                        val student = Student(
                            name = name,
                            email = email,
                            password = password
                        )
                        registerViewModel.saveStudent(student).observe(viewLifecycleOwner) {
                            if (it == true) {
                                requireContext().showToast("Registrasi Berhasil")
                                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                                    args.role
                                )
                                findNavController().navigate(action)
                            } else {
                                btnRegister.visible()
                                progressBar.visible()
                                requireContext().showToast("Registrasi Gagal")
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

                }
            }
            .addOnFailureListener {
                binding.btnRegister.visible()
                binding.progressBar.gone()
                requireContext().showToast(it.message.toString())
            }
    }
}