package com.example.latihan13.ui.meme

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan13.R
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.databinding.LayoutListMemeBinding

class MemeAdapter : RecyclerView.Adapter<MemeAdapter.ViewHolder>() {

    private var listData = ArrayList<Meme>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Meme>?) {
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListMemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Meme) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivMeme)
                tvTitle.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = LayoutListMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}