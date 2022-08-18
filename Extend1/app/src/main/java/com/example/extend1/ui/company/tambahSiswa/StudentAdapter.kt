package com.example.extend1.ui.company.tambahSiswa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.extend1.databinding.LayoutListStudentBinding
import com.example.extend1.model.Student

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.HomeViewHolder>() {

    private val datas = ArrayList<Student>()
    var onItemClick: ((Student) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<Student>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Student) {
            binding.apply {
                binding.student = data
                binding.executePendingBindings()

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            LayoutListStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}