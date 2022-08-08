package com.example.extend1.ui.admin.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentRegisterAdminBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterAdminFragment : Fragment() {

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
        auth = FirebaseAuth.getInstance()
        binding.apply {
            btnRegister.setOnClickListener {
                val nama = etNamaAdminRegister.text.toString()
                val npwp = etNpwpAdminRegister.text.toString()
                val email = etEmailAdminRegister.text.toString()
                val password = etPasswordAdminRegister.text.toString()

                if (nama.isEmpty()) {
                    etNamaAdminRegister.error = "Nama tidak bisa kosong"
                    etNamaAdminRegister.requestFocus()
                    return@setOnClickListener
                } else if (npwp.isEmpty()) {
                    etNpwpAdminRegister.error = "NPWP tidak bisa kosong"
                    etNpwpAdminRegister.requestFocus()
                    return@setOnClickListener
                } else if (email.isEmpty()) {
                    etEmailAdminRegister.error = "Email tidak bisa kosong"
                    etEmailAdminRegister.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailAdminRegister.error = "Email tidak sesuai"
                    etEmailAdminRegister.requestFocus()
                    return@setOnClickListener
                } else if (password.isEmpty()) {
                    etPasswordAdminRegister.error = "Password tidak bisa kosong"
                    etPasswordAdminRegister.requestFocus()
                    return@setOnClickListener
                } else {
                    registerFirebase(email, password, npwp, nama)
                }
            }
        }
    }

    private fun registerFirebase(email: String, password: String, npwp: String, nama: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(RegisterAdminFragmentDirections.actionRegisterAdminFragmentToLoginAdminFragment())
                } else {
                    Toast.makeText(requireContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                }
            }
    }


}