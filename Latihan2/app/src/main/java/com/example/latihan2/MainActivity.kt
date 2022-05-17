package com.example.latihan2

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.latihan2.adapter.GameAdapter
import com.example.latihan2.databinding.ActivityMainBinding
import com.example.latihan2.model.ResponGame
import com.example.latihan2.service.ServiceBuilder.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameAdapter = GameAdapter()
        binding.apply {
            recyclerView.adapter = gameAdapter
        }

        val client = apiService.getListGame("d3dca7b8e848473486e46f7dd30d6d1a", "200")
        client.enqueue(object : Callback<ResponGame> {
            override fun onResponse(call: Call<ResponGame>, response: Response<ResponGame>) {
                val listGame = response.body()?.results
                if (listGame != null){
                    gameAdapter.setListData(listGame)
                }
                Log.d(TAG, "onResponse: $listGame")
            }

            override fun onFailure(call: Call<ResponGame>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }
}