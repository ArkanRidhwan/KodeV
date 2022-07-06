package com.example.latihan13.ui.kartun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan13.databinding.FragmentKartunBinding
import com.example.latihan13.viewmodel.ViewModelFactory
import com.example.latihan13.vo.Status


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

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@KartunFragment, factory)[KartunViewModel::class.java]

        val kartunAdapter = KartunAdapter()
        viewModel.getKartun().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCESS -> {
                        binding.progressBar.visibility = View.GONE
                        it.data.let {
                            kartunAdapter.setData(it)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.recyclerView.apply {
                setHasFixedSize(true)
                adapter = kartunAdapter
            }
        }
    }
}