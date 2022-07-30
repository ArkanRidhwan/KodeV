package com.example.latihan15.ui.makeup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan15.core.data.Resource
import com.example.latihan15.databinding.FragmentMakeUpBinding
import com.example.latihan15.ui.ViewModelFactory

class MakeUpFragment : Fragment() {

    private lateinit var binding: FragmentMakeUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@MakeUpFragment, factory)[MakeUpViewModel::class.java]

        val makeUpAdapter = MakeUpAdapter()
        viewModel.getMakeup().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let {
                            makeUpAdapter.setData(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = makeUpAdapter
                }
            }
        }
    }
}