package com.example.latihan14.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan14.core.data.source.Resource
import com.example.latihan14.databinding.FragmentMainBinding
import com.example.latihan14.ui.ViewModelFactory

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@MainFragment, factory)[MainViewModel::class.java]

        val destinasiAdapter = MainAdapter()
        viewModel.getDestinasi().observe(viewLifecycleOwner) {
            if (it != null)
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let {
                            destinasiAdapter.setData(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            binding.recyclerView.apply {
                setHasFixedSize(true)
                adapter = destinasiAdapter
            }
        }
    }
}