package com.example.extend1.ui.main.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentSplashBinding
import com.example.extend1.utils.Constant.ID
import com.example.extend1.utils.Constant.ROLE
import com.example.extend1.utils.getInstance
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val role = getInstance(requireContext()).getString(ROLE)
        val userId = getInstance(requireContext()).getString(ID)

        Handler().postDelayed({
            if (userId.isNotEmpty()) {
                when (role) {
                    getString(R.string.company) -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeCompanyFragment())
                    }
                    getString(R.string.admin) -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeAdminFragment())
                    }
                    else -> {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeStudentFragment())
                    }
                }
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
            }
        }, 1000)
    }
}