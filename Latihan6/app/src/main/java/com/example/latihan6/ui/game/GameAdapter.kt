package com.example.latihan6.ui.game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan6.R
import com.example.latihan6.databinding.LayoutListGameBinding
import com.kodev.games.data.source.remote.response.DataGame

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var listGame = ArrayList<DataGame>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(games: List<DataGame>?){
        if (games == null) return
        this.listGame.clear()
        this.listGame.addAll(games)
        notifyDataSetChanged()
    }

    inner class GameViewHolder(private val binding: LayoutListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataGame) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image_black)
                    )
                    .into(imgGames)
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating.toString()
                tvReleaseDate.text = data.released
                for(i in data.genres){
                    tvGenreGame.text = i.name
                }
                for (i in data.platforms){
                    tvPlatformGame.text = i.platform.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemsBinding = LayoutListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val listGame = listGame[position]
        holder.bind(listGame)
    }

    override fun getItemCount(): Int = listGame.size
}