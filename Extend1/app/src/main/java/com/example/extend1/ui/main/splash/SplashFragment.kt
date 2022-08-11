package com.example.extend1.ui.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentSplashBinding
import com.example.extend1.utils.Constant.USER_ID
import com.example.extend1.utils.Constant.USER_ROLE
import com.example.extend1.utils.getInstance
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val role = getInstance(requireContext()).getString(USER_ROLE)
        val userId = getInstance(requireContext()).getString(USER_ID)

        if (userId.isNotEmpty()) {
            if (role == getString(R.string.admin))
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeAdminFragment())
            else
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeUserFragment())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
        }
    }
}