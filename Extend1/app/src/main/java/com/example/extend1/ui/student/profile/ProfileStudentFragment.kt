package com.example.extend1.ui.student.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentLoginBinding
import com.example.extend1.databinding.FragmentProfileStudentBinding
import com.example.extend1.ui.admin.profile.ProfileAdminFragmentDirections
import com.example.extend1.utils.getInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileStudentFragment : Fragment() {

    private lateinit var binding: FragmentProfileStudentBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileStudentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = Firebase.auth
        binding.ivLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        auth.signOut()
        getInstance(requireContext()).clearPreferences()
        findNavController().navigate(ProfileStudentFragmentDirections.actionProfileStudentFragmentToWelcomeFragment())
    }
}