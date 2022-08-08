package com.example.extend1.ui.user.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentRegisterUserBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterUserFragment : Fragment() {

    private lateinit var binding: FragmentRegisterUserBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnUserRegister.setOnClickListener {
                val nama = etNamaUserRegister.text.toString()
                val password = etPasswordUserRegister.text.toString()
                val email = etEmailUserRegister.text.toString()

                if (nama.isEmpty()) {
                    etNamaUserRegister.error = "Nama tidak bisa kosong"
                    etNamaUserRegister.requestFocus()
                    return@setOnClickListener
                } else if (email.isEmpty()) {
                    etEmailUserRegister.error = "Email tidak bisa kosong"
                    etEmailUserRegister.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailUserRegister.error = "Email tidak sesuai"
                    etEmailUserRegister.requestFocus()
                    return@setOnClickListener
                } else if (password.isEmpty()) {
                    etPasswordUserRegister.error = "Password tidak bisa kosong"
                    etPasswordUserRegister.requestFocus()
                    return@setOnClickListener
                } else {
                    registerFirebase(email, password)
                }
            }
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(RegisterUserFragmentDirections.actionRegisterUserFragmentToHomeUserFragment())
                } else {
                    Toast.makeText(requireContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}