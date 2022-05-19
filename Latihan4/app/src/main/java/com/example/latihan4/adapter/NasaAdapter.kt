package com.example.latihan4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan4.databinding.BahanRvNasaBinding
import com.example.latihan4.model.nasa.MainDataNasaItem

class NasaAdapter : RecyclerView.Adapter<NasaAdapter.ListViewHolder>() {
    private val listDataNasa = ArrayList<MainDataNasaItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<MainDataNasaItem>) {
        listDataNasa.clear()
        listDataNasa.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: BahanRvNasaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataNasa: MainDataNasaItem) {
            binding.apply {
                Glide.with(ivNasa)
                    .load(dataNasa.hdurl)
                    .into(ivNasa)

                tvTittleContent.text = dataNasa.title
                tvDateContent.text = dataNasa.date
                tvCopyrightContent.text = dataNasa.copyright
                tvExplanationContent.text = dataNasa.explanation
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            BahanRvNasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listDataNasa[position])
    }

    override fun getItemCount(): Int = listDataNasa.size
}
