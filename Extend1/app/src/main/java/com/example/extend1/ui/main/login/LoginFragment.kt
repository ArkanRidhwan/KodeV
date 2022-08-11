package com.example.extend1.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.extend1.R
import com.example.extend1.databinding.FragmentLoginBinding
import com.example.extend1.utils.Constant.ADMIN_ID
import com.example.extend1.utils.Constant.ADMIN_ROLE
import com.example.extend1.utils.Constant.USER_ID
import com.example.extend1.utils.Constant.USER_ROLE
import com.example.extend1.utils.getInstance
import com.example.extend1.utils.gone
import com.example.extend1.utils.showToast
import com.example.extend1.utils.visible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {

    companion object {
        private const val RC_SIGN_IN = 12
        private const val TAG = "LoginFragment"
    }

    private val loginViewModel: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignIn: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignIn = GoogleSignIn.getClient(requireActivity(), gso)

        auth = FirebaseAuth.getInstance()
        if (args.role == getString(R.string.user))
            binding.tvLoginTitleUser.visible()
        else
            binding.tvLoginTittleAdmin.visible()

        binding.apply {
            ivGoogleSignIn.setOnClickListener {
                signIn()
            }

            tvRegisterNow.setOnClickListener {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                        args.role
                    )
                findNavController().navigate(action)
            }

            btnLogin.setOnClickListener {
                val email = etEmailLogin.text.toString()
                val password = etPasswordLogin.text.toString()
                if (email.isEmpty()) {
                    etEmailLogin.error = "Email tidak bisa kosong"
                    etEmailLogin.requestFocus()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailLogin.error = "Email tidak sesuai"
                } else if (password.isEmpty()) {
                    etPasswordLogin.error = "Password tidak bisa kosong"
                    etPasswordLogin.requestFocus()
                } else {

                    if (args.role == getString(R.string.user)) {
                        btnLogin.gone()
                        progressCircular.visible()
                        loginViewModel.loginUserByEmailPassword(email, password)
                            .observe(viewLifecycleOwner) {
                                if (it != null) {
                                    loginFirebase(email, password)
                                    getInstance(requireContext()).putString(USER_ID, it.id)
                                    getInstance(requireContext()).putString(
                                        USER_ROLE,
                                        getString(R.string.user)
                                    )
                                } else {
                                    requireContext().showToast("Email atau Password Salah")
                                    binding.btnLogin.visible()
                                    binding.progressCircular.gone()
                                }
                            }
                    } else {
                        btnLogin.gone()
                        progressCircular.visible()
                        loginViewModel.loginAdminByEmailPassword(email, password)
                            .observe(viewLifecycleOwner) {
                                if (it != null) {
                                    loginFirebase(email, password)
                                    getInstance(requireContext()).putString(ADMIN_ID, it.id)
                                    getInstance(requireContext()).putString(
                                        ADMIN_ROLE,
                                        getString(R.string.admin)
                                    )
                                } else {
                                    requireContext().showToast("Email atau Password Salah")
                                    binding.btnLogin.visible()
                                    binding.progressCircular.gone()
                                }
                            }
                    }
                }
            }
        }
    }

    private fun signIn() {
        val signIn = googleSignIn.signInIntent
        startActivityForResult(signIn, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "onActivityResult: " + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Log.w(TAG, "onActivityResult: ", e)
                }
            } else {
                Log.w(TAG, exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "firebaseAuthWithGoogle: success")
                    if (args.role == getString(R.string.user))
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeUserFragment())
                    else
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeAdminFragment())
                } else {
                    Log.w(TAG, "firebaseAuthWithGoogle: failure", it.exception)
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
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeAdminFragment())
                    else
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeUserFragment())

                }
            }
            .addOnFailureListener {
                requireContext().showToast(it.message.toString())
            }
    }

}