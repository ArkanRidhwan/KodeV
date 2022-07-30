package com.example.latihan11.ui.kartun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan11.R
import com.example.latihan11.data.source.local.entity.KartunEntity
import com.example.latihan11.databinding.LayoutListKartunBinding

class KartunAdapter : PagedListAdapter<KartunEntity, KartunAdapter.KartunViewHolder>(DIFF_CALLBACK) {

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

    inner class KartunViewHolder(private val binding: LayoutListKartunBinding) :
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
                tvSpecies.text = data.species
            }
        }

    }

    override fun onBindViewHolder(holder: KartunViewHolder, position: Int) {
        val kartun = getItem(position)
        if (kartun != null)
            holder.bind(kartun)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KartunViewHolder {
        val itemsBinding =
            LayoutListKartunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KartunViewHolder(itemsBinding)
    }
}