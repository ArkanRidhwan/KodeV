package com.example.latihan61.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.databinding.ItemExploreBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ExploreViewHolder>() {

    private var listUsers = ArrayList<ResponseHomeItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<ResponseHomeItem>?) {
        if (users == null) return
        this.listUsers.clear()
        this.listUsers.addAll(users)
        notifyDataSetChanged()
    }

    inner class ExploreViewHolder(private val binding: ItemExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseHomeItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.avatar_url)
                    .into(imgPostingan)
                Glide.with(itemView.context)
                    .load(data.avatar_url)
                    .into(imgPictureProfile)
                tvUsernameProfile.text = data.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size
}