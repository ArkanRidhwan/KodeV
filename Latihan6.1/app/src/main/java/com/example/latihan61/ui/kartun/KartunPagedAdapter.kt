package com.example.latihan61.ui.kartun

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan61.data.remote.response.kartun.Result
import com.example.latihan61.databinding.ItemKartunBinding

class KartunPagedAdapter(private val activity: Activity) :
    PagedListAdapter<Result, KartunPagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(private val binding: ItemKartunBinding) :
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

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Result> =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.image == newItem.image && oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onBindViewHolder(holder: KartunPagedAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null){
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KartunPagedAdapter.ViewHolder {
        val binding = ItemKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}