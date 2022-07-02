package com.example.latihan121.ui.kartun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan121.R
import com.example.latihan121.data.source.local.entity.KartunEntity
import com.example.latihan121.databinding.LayoutListKartunBinding

class KartunAdapter : PagedListAdapter<KartunEntity, KartunAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<KartunEntity>() {
            override fun areItemsTheSame(oldItem: KartunEntity, newItem: KartunEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: KartunEntity, newItem: KartunEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(private val binding: LayoutListKartunBinding) :
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
                tvStatusContent.text = data.status
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsBinding =
            LayoutListKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsBinding)
    }
}