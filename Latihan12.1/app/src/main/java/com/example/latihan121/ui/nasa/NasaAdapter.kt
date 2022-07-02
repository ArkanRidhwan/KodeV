package com.example.latihan121.ui.nasa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan121.R
import com.example.latihan121.data.source.local.entity.NasaEntity
import com.example.latihan121.databinding.LayoutListNasaBinding

class NasaAdapter : RecyclerView.Adapter<NasaAdapter.ViewHolder>() {

    private var listData = ArrayList<NasaEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<NasaEntity>?) {
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListNasaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NasaEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.hdurl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivGambar)
                tvTittle.text = data.title
                tvExplanation.text = data.explanation
                tvCopyright.text = data.copyright
                tvDate.text = data.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = LayoutListNasaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}