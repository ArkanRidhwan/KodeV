package com.example.latihan11.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan11.databinding.FragmentGameBinding
import com.example.latihan11.ui.detail.DetailGameActivity
import com.example.latihan11.viemodel.ViewModelFactory
import com.example.latihan11.vo.Status


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
        viewModel.getGames().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    Status.SUCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        it.data.let {
                            gameAdapter.submitList(it)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(context, "Terjadi Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.rvGame.apply {
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        gameAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, it)
            requireActivity().startActivity(intent)
        }
    }
}