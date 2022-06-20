package com.example.latihan6.data.source.remote.response

import android.content.ContentValues.TAG
import android.util.Log
import com.example.latihan6.data.source.remote.api.ApiConfig.getApiServiceGame
import com.kodev.games.data.source.remote.response.ResponseGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }
    }

    fun getGames(callback: LoadGetGames){
        val client = getApiServiceGame().getGames("d3dca7b8e848473486e46f7dd30d6d1a","20")
        client.enqueue(object : Callback<ResponseGame>{
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.onLoadGetGames(it)
                    }
                }else{
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }

    interface LoadGetGames {
        fun onLoadGetGames(listGames: ResponseGame)
    }
}