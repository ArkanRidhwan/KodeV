package com.example.latihan2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan2.databinding.ListGamesBinding
import com.example.latihan2.model.DataGame

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {
    private val listData = ArrayList<DataGame>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<DataGame>){
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ListGamesBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(data: DataGame){
                binding.apply {
                    Glide.with(ivRvImage)
                        .load(data.background_image)
                        .into(ivRvImage)

                    tvRvRating.text = data.rating.toString()
                    tvRvTittle.text = data.name
                    tvRvReleaseDateContent.text = data.released
                    val genre = ArrayList<String>()
                    for (i in data.genres) {
                        genre.add(i.name)
                    }
                    tvRvGenresContent.text = genre.toString().replace("[", "").replace("]","")
                    tvRvChartContent.text = data.rating_top.toString()
                    val stores = ArrayList<String>()
                    for (i in data.stores){
                        stores.add(i.store.name)
                    }
                    tvRvStores.text = stores.toString()

                    tvRvRequirementContent.text = data.platforms[0].requirements_en?.recommended

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}