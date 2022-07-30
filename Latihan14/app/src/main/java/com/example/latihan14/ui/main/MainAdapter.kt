package com.example.latihan14.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan14.R
import com.example.latihan14.core.domain.model.Destinasi
import com.example.latihan14.databinding.LayoutListMainBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listData = ArrayList<Destinasi>()

    fun setData(data: List<Destinasi>?) {
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutListMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Destinasi) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivImage)
                tvId.text = data.id.toString()
                tvName.text = data.name
                tvAddress.text = data.address
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = LayoutListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}