package com.example.latihan15.ui.dicoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.latihan15.core.data.Resource
import com.example.latihan15.databinding.FragmentStoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class StoryFragment : Fragment() {

    private lateinit var binding: FragmentStoryBinding
    private val viewModel: StoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@StoryFragment, factory)[StoryViewModel::class.java]*/

        loadStory()
    }

    private fun loadStory() {
        val storyAdapter = StoryAdapter()
        viewModel.getStory().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let {
                            storyAdapter.setData(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = storyAdapter
                }

                binding.floatingActionButton.setOnClickListener {
                    findNavController().navigate(StoryFragmentDirections.actionStoryFragmentToAddStoryFragment())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadStory()
    }
}