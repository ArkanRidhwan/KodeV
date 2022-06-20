package com.example.latihan8.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan8.data.local.Data
import com.example.latihan8.databinding.ItemRvBinding

class HomePagedAdapter(private val activity: Activity) :
    PagedListAdapter<Data.Dummy1, HomePagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data.Dummy1) {
            binding.apply {
                Glide.with(circleImageView)
                    .load(data.img)
                    .into(circleImageView)
                textView.text = data.name
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Data.Dummy1> =
            object : DiffUtil.ItemCallback<Data.Dummy1>() {
                override fun areItemsTheSame(oldItem: Data.Dummy1, newItem: Data.Dummy1): Boolean {
                    return oldItem.img == newItem.img && oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: Data.Dummy1,
                    newItem: Data.Dummy1
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: HomePagedAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagedAdapter.ViewHolder {
        val binding= ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}