package com.example.extend1.ui.user.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentLoginUserBinding
import com.google.firebase.auth.FirebaseAuth


class LoginUserFragment : Fragment() {

    private lateinit var binding: FragmentLoginUserBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            tvRegisterNow.setOnClickListener {
                findNavController().navigate(LoginUserFragmentDirections.actionLoginUserFragmentToRegisterUserFragment())
            }
            btnUserLogin.setOnClickListener {
                val email = etEmailUserLogin.text.toString()
                val password = etPasswordUserLogin.text.toString()

                if (email.isEmpty()) {
                    etEmailUserLogin.error = "Email tidak bisa kosong"
                    etEmailUserLogin.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailUserLogin.error = "Email tidak sesuai"
                    etEmailUserLogin.requestFocus()
                    return@setOnClickListener
                } else if (password.isEmpty()) {
                    etPasswordUserLogin.error = "Password tidak bisa kosong"
                    etPasswordUserLogin.requestFocus()
                    return@setOnClickListener
                } else {
                    loginFirebase(email, password)
                }
            }
        }
    }

    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginUserFragmentDirections.actionLoginUserFragmentToHomeUserFragment())
                } else {
                    Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}