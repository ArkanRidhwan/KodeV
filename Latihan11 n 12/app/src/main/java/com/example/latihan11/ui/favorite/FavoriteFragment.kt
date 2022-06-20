package com.example.latihan11.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihan11.databinding.FragmentFavoriteBinding
import com.example.latihan11.ui.detail.DetailGameActivity
import com.example.latihan11.ui.games.GameViewModel
import com.example.latihan11.viemodel.ViewModelFactory
import com.example.latihan11.vo.Status

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@FavoriteFragment, factory)[GameViewModel::class.java]

        val favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
        }

        favoriteAdapter.onItemClick = {
            val intent = Intent(requireActivity(), DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, it)
            requireActivity().startActivity(intent)
        }

        viewModel.getFavoriteGame().observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCESS -> {
                        binding.progressBar.visibility = View.GONE
                        it.data.let {
                            favoriteAdapter.submitList(it)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}