package com.example.latihan6.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan6.databinding.FragmentGameBinding
import com.example.latihan6.viewmodel.ViewModelFactory


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@GameFragment, factory)[GameViewModel::class.java]

        val gameAdapter = GameAdapter()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getGames().observe(viewLifecycleOwner) {
            gameAdapter.setData(it.results)
            binding.progressBar.visibility = View.GONE
        }


        binding.rvGame.apply {
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }
}