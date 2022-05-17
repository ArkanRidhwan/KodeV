package com.example.latihan1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter : RecyclerView.Adapter<Adapter.ListViewHolder>() {

    var onItemClick: ((DataMainActivity.User) ->Unit)? = null
    private val listData = ArrayList<DataMainActivity.User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<DataMainActivity.User>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsernameUser: TextView = itemView.findViewById(R.id.tv_username)
        var imgUser: ImageView = itemView.findViewById(R.id.iv_upload)
        var tvTotalLikesUser: TextView = itemView.findViewById(R.id.tv_total_likes)
        var tvTittleUploadUser: TextView = itemView.findViewById(R.id.tv_tittleupload)
//        var tvContentUploadUser: TextView = itemView.findViewById(R.id.tv_contentupload)
        var tvTotalCommentUser: TextView = itemView.findViewById(R.id.tv_totalcomments)

        fun bind(data: DataMainActivity.User) {
            tvUsernameUser.text = data.username
            Glide.with(imgUser)
                .load(data.gambar)
                .into(imgUser)
            tvTotalLikesUser.text = data.suka
            tvTittleUploadUser.text = itemView.context.getString(R.string.contain_username_postingan, data.username, data.postingan)
            tvTotalCommentUser.text = data.totalComment

            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}