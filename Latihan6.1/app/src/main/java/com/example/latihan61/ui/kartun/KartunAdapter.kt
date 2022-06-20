package com.example.latihan61.ui.kartun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan61.data.remote.response.kartun.Result
import com.example.latihan61.databinding.ItemKartunBinding

class KartunAdapter : RecyclerView.Adapter<KartunAdapter.KartunViewHolder>() {

    private var listCharacters = ArrayList<Result>()

    fun setData(list: List<Result>?) {
        if (list == null) return
        this.listCharacters.clear()
        this.listCharacters.addAll(list)
        notifyDataSetChanged()
    }

    class KartunViewHolder(private val binding: ItemKartunBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Result) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivKartun)
                tvNameContent.text = data.name
                tvCreatedContent.text = data.created
                tvOriginContent.text = data.origin.name
                tvStatusContent.text = data.status
                tvSpecies.text = data.species
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KartunViewHolder {
        val binding = ItemKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KartunViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KartunViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }

    override fun getItemCount(): Int = listCharacters.size
}