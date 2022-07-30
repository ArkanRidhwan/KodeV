package com.example.latihan15.ui.dicoding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan15.R
import com.example.latihan15.core.domain.model.Story
import com.example.latihan15.databinding.LayoutListStoryBinding

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private var listData = ArrayList<Story>()

    fun setData(data: List<Story>?) {
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutListStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Story) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivImg)
                tvId.text = "Id: ${data.id}"
                tvName.text = "Name: ${data.name}"
                tvDate.text = "Date: ${data.date}"
                tvDescription.text = "Description: ${data.description}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding =
            LayoutListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}