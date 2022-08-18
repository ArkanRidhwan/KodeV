package com.example.extend1.ui.student.admin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.extend1.databinding.LayoutListAdminBinding
import com.example.extend1.databinding.LayoutListStudentBinding
import com.example.extend1.model.Admin
import com.example.extend1.model.Student

class AdminAdapter : RecyclerView.Adapter<AdminAdapter.HomeViewHolder>() {

    private val datas = ArrayList<Admin>()
    var onItemClick: ((Admin) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<Admin>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Admin) {
            binding.apply {
                binding.admin = data
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            LayoutListAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}