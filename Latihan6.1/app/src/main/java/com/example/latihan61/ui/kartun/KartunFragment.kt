package com.example.latihan61.ui.kartun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihan61.databinding.FragmentKartunBinding
import com.example.latihan61.viewmodel.ViewModelFactory


class KartunFragment : Fragment() {

    private lateinit var binding: FragmentKartunBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKartunBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@KartunFragment, factory)[KartunViewModel::class.java]
        val kartunAdapter = KartunAdapter()

        viewModel.getKartuns().observe(viewLifecycleOwner) {
            kartunAdapter.setData(it.results)
        }

        binding.rvKartun.apply {
            setHasFixedSize(true)
            adapter = kartunAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}