package com.example.latihan11.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan11.R
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.databinding.ActivityDetailGameBinding
import com.example.latihan11.ui.games.GameViewModel
import com.example.latihan11.viemodel.ViewModelFactory
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs

class DetailGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGameBinding
    private lateinit var data: GameEntity
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var factory = ViewModelFactory.getInstance(this@DetailGameActivity)
        val viewModel =
            ViewModelProvider(this@DetailGameActivity, factory)[GameViewModel::class.java]

        binding.apply {
            toolbar.setNavigationOnClickListener {
                this@DetailGameActivity.onBackPressed()
            }
            imgButtonBack.setOnClickListener {
                this@DetailGameActivity.onBackPressed()
            }
            floatingActionButton.setOnClickListener {
                viewModel.updateGame(data, !isFavorite)
                populateFabButton(!isFavorite)
                if (isFavorite)
                    Snackbar.make(it, "Berhasil dihapus dari favorit", Snackbar.LENGTH_SHORT).show()
                else
                    Snackbar.make(it, "Berhasil ditambah ke favorit", Snackbar.LENGTH_SHORT).show()
            }

            populateToolbar()
            populateView()
        }
    }

    private fun populateToolbar() {
        binding.apply {
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    toolbar.visibility = View.GONE
                } else {
                    toolbar.visibility = View.GONE
                }
            })
        }
    }

    private fun populateView() {
        data = intent.getParcelableExtra<GameEntity>(EXTRA_DATA)!!
        if (data != null) {
            binding.apply {
                Glide.with(this@DetailGameActivity)
                    .load(data.background_image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(imgGames)

                toolbar.title = data.name
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating
                tvReleaseDate.text = data.released
                tvGenreGame.text = data.genres
                tvMinimumGame.text = data.minimum

                isFavorite = data.favorite
                populateFabButton(isFavorite)
            }
        }
    }

    private fun populateFabButton(favorite: Boolean) {
        if (favorite)
            binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite)
        else
            binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite_border)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}