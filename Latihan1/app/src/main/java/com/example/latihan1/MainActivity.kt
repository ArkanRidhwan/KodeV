package com.example.latihan1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private lateinit var rvUser: RecyclerView
    private lateinit var tvSeeMore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter()
        rvUser = findViewById(R.id.rv_user)
        //tvSeeMore = findViewById(R.id.tv_aboutUser)

        adapter.setListData(DataMainActivity.generateDataUser())

        rvUser.layoutManager = LinearLayoutManager (this@MainActivity, LinearLayoutManager.VERTICAL, false)
        rvUser.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        /*tvSeeMore.setOnClickListener {
            val intent = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(intent)
        }*/
    }
}