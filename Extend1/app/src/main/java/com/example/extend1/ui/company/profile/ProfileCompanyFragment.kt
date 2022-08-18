package com.example.extend1.ui.company.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentProfileCompanyBinding
import com.example.extend1.ui.admin.profile.ProfileAdminFragmentDirections
import com.example.extend1.ui.main.MainActivity
import com.example.extend1.utils.getInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileCompanyFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentProfileCompanyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileCompanyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.ivLogout.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        auth.signOut()
        getInstance(requireContext()).clearPreferences()
        findNavController().navigate(ProfileCompanyFragmentDirections.actionProfileCompanyFragmentToWelcomeFragment())
    }
}