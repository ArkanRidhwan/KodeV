package com.example.latihan121.ui.nasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan121.databinding.FragmentNasaBinding
import com.example.latihan121.viewmodel.ViewModelFactory
import com.example.latihan121.vo.Status

class NasaFragment : Fragment() {

    private lateinit var binding: FragmentNasaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNasaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@NasaFragment, factory)[NasaViewModel::class.java]

        val nasaAdapter = NasaAdapter()
        viewModel.getNasa().observe(viewLifecycleOwner) { it ->
            if (it != null) {
                when(it.status){
                    Status.LOADING -> {
                        binding.progressBar2.visibility = View.VISIBLE
                    }
                    Status.SUCESS -> {
                        binding.progressBar2.visibility = View.GONE
                        it.data.let {
                            nasaAdapter.setData(it)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar2.visibility = View.GONE
                    }
                }
            }
        }
        binding.rvNasa.apply {
            setHasFixedSize(true)
            adapter =nasaAdapter
        }
    }
}