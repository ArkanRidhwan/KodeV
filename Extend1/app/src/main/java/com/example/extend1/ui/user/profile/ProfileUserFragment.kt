package com.example.extend1.ui.user.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.extend1.R
import com.example.extend1.databinding.FragmentProfileAdminBinding
import com.example.extend1.databinding.FragmentProfileUserBinding
import com.example.extend1.ui.main.MainActivity
import com.example.extend1.utils.getInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileUserFragment : Fragment() {


    private lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentProfileUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.textView6.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        auth.signOut()
        getInstance(requireContext()).clearPreferences()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }

}