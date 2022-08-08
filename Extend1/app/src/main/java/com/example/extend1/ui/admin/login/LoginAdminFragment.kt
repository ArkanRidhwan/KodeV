package com.example.extend1.ui.admin.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentLoginAdminBinding
import com.google.firebase.auth.FirebaseAuth


class LoginAdminFragment : Fragment() {

    private lateinit var binding: FragmentLoginAdminBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginAdminBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        binding.apply {
            tvRegisterNow.setOnClickListener {
                findNavController().navigate(LoginAdminFragmentDirections.actionLoginAdminFragmentToRegisterAdminFragment())
            }
            btnAdminLogin.setOnClickListener {
                val email = etEmailAdminLogin.text.toString()
                val password = etPasswordAdminLogin.text.toString()

                if (email.isEmpty()) {
                    etEmailAdminLogin.error = "Email tidak bisa kosong"
                    etEmailAdminLogin.requestFocus()
                    return@setOnClickListener
                }



                if (password.isEmpty()) {
                    etPasswordAdminLogin.error = "Password tidak bisa kosong"
                    etPasswordAdminLogin.requestFocus()
                    return@setOnClickListener
                }
                LoginFirebase(email, password)
            }
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginAdminFragmentDirections.actionLoginAdminFragmentToHomeAdminFragment())
                } else {
                    Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }
}