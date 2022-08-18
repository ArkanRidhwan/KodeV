package com.example.extend1.ui.main.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.R
import com.example.extend1.databinding.FragmentWelcomeBinding
import com.example.extend1.ui.company.home.HomeCompanyFragmentDirections

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
            btnCompany.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(getString(R.string.company))
                findNavController().navigate(action)
            }
            btnAdmin.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(getString(R.string.admin))
                findNavController().navigate(action)
            }

            btnStudent.setOnClickListener {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment(getString(R.string.student))
                findNavController().navigate(action)
            }
        }

    }
}