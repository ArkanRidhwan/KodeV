package com.example.latihan61.ui.home

import  android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.latihan61.R
import com.example.latihan61.databinding.FragmentHomeBinding
import com.example.latihan61.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@HomeFragment, factory)[HomeViewModel::class.java]
        val homeAdapter = HomeAdapter()

        viewModel.getUsers().observe(viewLifecycleOwner) {
            homeAdapter.setData(it)
        }

        binding.rvHome.apply {
            setHasFixedSize(true)
            adapter = homeAdapter

        }
        binding.btnPindah.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_kartunFragment)
        )
    }
}