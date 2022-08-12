package com.example.extend1.ui.user.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.extend1.databinding.FragmentHomeUserBinding

class HomeUserFragment : Fragment() {

    private lateinit var binding: FragmentHomeUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            fabUser.setOnClickListener {
                findNavController().navigate(HomeUserFragmentDirections.actionHomeUserFragmentToChatFragment())
            }
        }
    }
}