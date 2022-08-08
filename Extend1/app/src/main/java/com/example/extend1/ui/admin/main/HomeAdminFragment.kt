package com.example.extend1.ui.admin.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentChatBinding
import com.example.extend1.databinding.FragmentHomeAdminBinding
import com.example.extend1.ui.WelcomeFragmentDirections


class HomeAdminFragment : Fragment() {

    private lateinit var binding: FragmentHomeAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeAdminBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            fabAdmin.setOnClickListener{
                findNavController().navigate(HomeAdminFragmentDirections.actionHomeAdminFragmentToChatFragment())
            }
        }
    }
}