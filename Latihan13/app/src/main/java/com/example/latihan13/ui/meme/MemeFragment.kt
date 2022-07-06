package com.example.latihan13.ui.meme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan13.databinding.FragmentMemeBinding
import com.example.latihan13.viewmodel.ViewModelFactory
import com.example.latihan13.vo.Status

class MemeFragment : Fragment() {

    private lateinit var binding: FragmentMemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@MemeFragment, factory)[MemeViewModel::class.java]

        val memeAdapter = MemeAdapter()
        viewModel.getMeme().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar3.visibility = View.VISIBLE
                    }
                    Status.SUCESS -> {
                        binding.progressBar3.visibility = View.GONE
                        it.data.let {
                            memeAdapter.setData(it)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar3.visibility = View.GONE
                        Toast.makeText(context, "Terjadi Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.recyclerView.apply {
                setHasFixedSize(true)
                adapter = memeAdapter
            }
        }
    }
}