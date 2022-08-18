package com.example.extend1.ui.main.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.extend1.databinding.LayoutListAdminBinding
import com.example.extend1.databinding.LayoutListChatBinding
import com.example.extend1.databinding.LayoutListStudentBinding
import com.example.extend1.model.Admin
import com.example.extend1.model.Chat
import com.example.extend1.model.Student

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.HomeViewHolder>() {

    private val datas = ArrayList<Chat>()
    var onItemClick: ((Chat) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<Chat>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Chat) {
            binding.apply {
                binding.chat = data
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            LayoutListChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}