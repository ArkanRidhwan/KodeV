package com.example.extend1.ui.main.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.extend1.R
import com.example.extend1.databinding.FragmentLoginBinding
import com.example.extend1.utils.Constant.ID
import com.example.extend1.utils.Constant.NAME
import com.example.extend1.utils.Constant.ROLE
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
        private const val TAG = "LoginFragment"
    }

    private val loginViewModel: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = FirebaseAuth.getInstance()

        when (args.role) {
            getString(R.string.admin) -> binding.tvLoginTitleAdmin.visible()
            getString(R.string.company) -> binding.tvLoginTittleCompany.visible()
            else -> {
                binding.tvLoginTittleStudent.visible()
            }
        }


        binding.apply {
            ivGoogleSignIn.setOnClickListener {
                signIn()
            }

            tvRegisterNow.setOnClickListener {
                val action =
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                        args.role, null
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
                    btnLogin.gone()
                    progressCircular.visible()

                    when (args.role) {
                        getString(R.string.admin) -> {
                            loginViewModel.loginAdminByEmailPassword(email, password)
                                .observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        loginFirebase()
                                        getInstance(requireContext()).putString(ID, it.id)
                                        getInstance(requireContext()).putString(NAME, it.name)
                                        getInstance(requireContext()).putString(ROLE, getString(R.string.admin))
                                    } else {
                                        requireContext().showToast("Email atau Password Salah")
                                        binding.btnLogin.visible()
                                        binding.progressCircular.gone()
                                    }
                                }
                        }
                        getString(R.string.company) -> {
                            loginViewModel.loginCompanyByEmailPassword(email, password)
                                .observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        loginFirebase()
                                        getInstance(requireContext()).putString(ID, it.id)
                                        getInstance(requireContext()).putString(NAME, it.name)
                                        getInstance(requireContext()).putString(ROLE, getString(R.string.company))
                                    } else {
                                        requireContext().showToast("Email atau password salah")
                                        binding.btnLogin.visible()
                                        binding.progressCircular.gone()
                                    }
                                }
                        }
                        getString(R.string.student) -> {
                            loginViewModel.loginStudentByEmailPassword(email, password)
                                .observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        loginFirebase()
                                        getInstance(requireContext()).putString(ID, it.id)
                                        getInstance(requireContext()).putString(NAME, it.name)
                                        getInstance(requireContext()).putString(ROLE, getString(R.string.student))
                                    } else {
                                        requireContext().showToast("Email atau password salah")
                                        binding.btnLogin.visible()
                                        binding.progressCircular.gone()
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                } catch (e: ApiException) {
                    Log.w(TAG, "Google sign in failed", e)
                }
            }
        }

    private fun firebaseAuthWithGoogle(idToken: String) {
        binding.btnLogin.gone()
        binding.progressCircular.visible()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser?.email
                    updateUI(user.toString())
                    Log.d(TAG, "signInWithCredential:success")
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun updateUI(email: String) {
        when (args.role) {
            getString(R.string.admin) -> {
                loginViewModel.loginAdminByEmail(email).observe(viewLifecycleOwner) {
                    if (it != null) {
                        getInstance(requireContext()).putString(ID, it.id)
                        getInstance(requireContext()).putString(NAME, it.name)
                        getInstance(requireContext()).putString(ROLE, getString(R.string.admin))
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeAdminFragment())
                        requireContext().showToast("Login Berhasil, Welcome ${auth.currentUser?.email}")
                    } else {
                        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                            args.role,
                            email
                        )
                        findNavController().navigate(action)
                        requireContext().showToast("User Belum Terdaftar, Harap Lengkapi Data Diri")
                    }
                    binding.btnLogin.visible()
                    binding.progressCircular.gone()
                }
            }
            getString(R.string.company) -> {
                loginViewModel.loginCompanyByEmail(email).observe(viewLifecycleOwner) {
                    if (it != null) {
                        getInstance(requireContext()).putString(ID, it.id)
                        getInstance(requireContext()).putString(NAME, it.name)
                        getInstance(requireContext()).putString(ROLE, getString(R.string.company))
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeCompanyFragment())
                        requireContext().showToast("Login Berhasil, Welcome ${auth.currentUser?.email}")
                    } else {
                        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                            args.role,
                            email
                        )
                        findNavController().navigate(action)
                        requireContext().showToast("Admin Belum Terdaftar, Harap Lengkapi Data Diri")
                    }
                    binding.btnLogin.visible()
                    binding.progressCircular.gone()
                }
            }
            getString(R.string.student) -> {
                loginViewModel.loginAdminByEmail(email).observe(viewLifecycleOwner) {
                    if (it != null) {
                        getInstance(requireContext()).putString(ID, it.id)
                        getInstance(requireContext()).putString(NAME, it.name)
                        getInstance(requireContext()).putString(ROLE, getString(R.string.student))
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeStudentFragment())
                        requireContext().showToast("Login Berhasil, Welcome ${auth.currentUser?.email}")
                    } else {
                        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                            args.role,
                            email
                        )
                        findNavController().navigate(action)
                        requireContext().showToast("Student belum terdaftar, harap lengkapi data diri")
                    }
                    binding.btnLogin.visible()
                    binding.progressCircular.gone()
                }
            }
        }
    }

    // Login with authentication
    private fun loginFirebase() {
        requireContext().showToast("Login Berhasil")
        when (args.role) {
            getString(R.string.company) -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeCompanyFragment())
            getString(R.string.student) -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeStudentFragment())
            else -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeAdminFragment())
        }
    }

}