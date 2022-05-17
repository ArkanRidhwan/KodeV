package com.example.latihan3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan3.databinding.BahanrvGambarKartunBinding
import com.example.latihan3.model.gambarKartun.MainDataKartun

class KartunAdapter : RecyclerView.Adapter<KartunAdapter.ListViewHolder>() {
    private val listDataKartun = ArrayList<MainDataKartun>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MainDataKartun>){
        listDataKartun.clear()
        listDataKartun.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: BahanrvGambarKartunBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(dataKartun: MainDataKartun){
                binding.apply {
                    Glide.with(ivKartun)
                        .load(dataKartun.image)
                        .into(ivKartun)

                    tvNameContent.text = dataKartun.name
                    tvCreatedContent.text = dataKartun.created
                    tvOriginContent.text = dataKartun.origin.name
                    tvStatusContent.text = dataKartun.status
                    tvSpecies.text = dataKartun.species
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KartunAdapter.ListViewHolder {
        val binding = BahanrvGambarKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KartunAdapter.ListViewHolder, position: Int) {
        holder.bind(listDataKartun[position])
    }

    override fun getItemCount(): Int = listDataKartun.size
}