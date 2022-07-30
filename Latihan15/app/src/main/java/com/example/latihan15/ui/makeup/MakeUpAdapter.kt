package com.example.latihan15.ui.makeup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.latihan15.R
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.databinding.LayoutListMakeupBinding

class MakeUpAdapter : RecyclerView.Adapter<MakeUpAdapter.ViewHolder>() {

    private var listData = ArrayList<Makeup>()

    fun setData(data: List<Makeup>?) {
        listData.clear()
        if (data != null) {
            listData.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutListMakeupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Makeup) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivImg)
                tvId.text = data.id.toString()
                tvName.text = "Name: ${data.name}"
                tvPrice.text = "Price: ${data.price}"
                tvDescription.text = "Description: ${data.description}"
                tvRating.text = "Rating: ${data.rating}"
                tvHexValue.text = "Hex: ${data.hexValue?:"No Copyright"}"
                tvColourName.text = "Colour: ${data.colourName?:"No Copyright"}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = LayoutListMakeupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}