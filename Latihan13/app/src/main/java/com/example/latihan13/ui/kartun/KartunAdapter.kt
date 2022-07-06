package com.example.latihan13.ui.kartun

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan13.R
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.databinding.LayoutListKartunBinding

class KartunAdapter : RecyclerView.Adapter<KartunAdapter.ViewHolder>() {

    private var listData = ArrayList<Kartun>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Kartun>?){
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListKartunBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Kartun) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivKartun)
                /*Glide.with(itemView.context)
                    .load(data.image)
                    .apply(RequestOptions().override(600, 200))
                    .into(ivKartun)*/
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