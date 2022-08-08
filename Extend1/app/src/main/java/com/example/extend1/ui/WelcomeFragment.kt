package com.example.extend1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAdmin.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginAdminFragment(getString(R.string.admin))
                findNavController().navigate(action)
            }
            btnUser.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginAdminFragment(getString(R.string.user))
                findNavController().navigate(action)
            }
        }

    }
}