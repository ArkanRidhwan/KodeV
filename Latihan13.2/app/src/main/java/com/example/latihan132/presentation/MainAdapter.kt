package com.example.latihan132.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan132.R
import com.example.latihan132.databinding.LayoutListKartunBinding
import com.example.latihan132.domain.KartunEntity

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listData = ArrayList<KartunEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<KartunEntity>?){
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutListKartunBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: KartunEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivKartun)
                tvNameContent.text = data.name
                tvCreatedContent.text = data.created
                tvOriginContent.text = data.originName
                tvStatusContent.text = data.status
                tvSpecies.text = data.species
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = LayoutListKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}