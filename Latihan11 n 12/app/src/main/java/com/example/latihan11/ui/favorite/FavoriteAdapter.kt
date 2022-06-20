package com.example.latihan11.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan11.R
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.databinding.LayoutListGameFavoriteBinding

class FavoriteAdapter() :
    PagedListAdapter<GameEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((GameEntity) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameEntity>() {
            override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FavoriteViewHolder(private val binding: LayoutListGameFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgGames)
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating
                tvReleaseDate.text = data.released
                tvGenreGame.text = data.genres
                tvPlatformGame.text = data.platforms
                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding =
            LayoutListGameFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.bind(game)
        }
    }
}