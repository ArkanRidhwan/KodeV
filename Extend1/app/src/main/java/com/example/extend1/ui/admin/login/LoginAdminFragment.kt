package com.example.extend1.ui.admin.login

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
import com.example.extend1.databinding.FragmentLoginAdminBinding
import com.example.extend1.utils.Constant.USER_ID
import com.example.extend1.utils.Constant.USER_ROLE
import com.example.extend1.utils.getInstance
import com.example.extend1.utils.gone
import com.example.extend1.utils.showToast
import com.example.extend1.utils.visible
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginAdminFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val args: LoginAdminFragmentArgs by navArgs()
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
                val action = LoginAdminFragmentDirections.actionLoginAdminFragmentToRegisterAdminFragment(args.role)
                findNavController().navigate(action)
            }
            btnAdminLogin.setOnClickListener {
                if (args.role == getString(R.string.user)) {
                    val email = etEmailAdminLogin.text.toString()
                    val password = etPasswordAdminLogin.text.toString()
                    if (email.isEmpty()) {
                        etEmailAdminLogin.error = "Email tidak bisa kosong"
                        etEmailAdminLogin.requestFocus()
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        etEmailAdminLogin.error = "Email tidak sesuai"
                    } else if (password.isEmpty()) {
                        etPasswordAdminLogin.error = "Password tidak bisa kosong"
                        etPasswordAdminLogin.requestFocus()
                    } else {
                        binding.btnAdminLogin.gone()
                        binding.progressCircular.visible()
                        loginViewModel.loginUserByEmailPassword(email, password).observe(viewLifecycleOwner) {
                            if (it != null) {
                                loginFirebase(email, password)
                                getInstance(requireContext()).putString(USER_ID, it.id)
                                getInstance(requireContext()).putString(USER_ROLE, getString(R.string.user))
                            } else {
                                requireContext().showToast("Email atau Password Salah")
                                binding.btnAdminLogin.visible()
                                binding.progressCircular.gone()
                            }
                        }
                    }
                } else {
                    // login as admin

                }
            }
        }
    }

    // Login with authentication
    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    requireContext().showToast("Login Berhasil, Welcome ${auth.currentUser?.email}")
                    if (args.role == getString(R.string.admin))
                        findNavController().navigate(LoginAdminFragmentDirections.actionLoginAdminFragmentToHomeAdminFragment())
                    else
                        findNavController().navigate(LoginAdminFragmentDirections.actionLoginAdminFragmentToHomeUserFragment())

                }
            }
            .addOnFailureListener {
                requireContext().showToast(it.message.toString())
            }
    }
}