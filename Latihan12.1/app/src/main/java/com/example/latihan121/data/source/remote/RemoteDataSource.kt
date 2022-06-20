package com.example.latihan121.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan121.data.source.remote.api.ApiConfig
import com.example.latihan121.data.source.remote.response.game.ResponseGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getGame(): LiveData<ApiResponse<ResponseGame>>{
        val responseGame = MutableLiveData<ApiResponse<ResponseGame>>()
        val client = ApiConfig.getApiServiceGame().getGames("d084045ca6164bbeb97021752a930416")
        client.enqueue(object : Callback<ResponseGame>{
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        responseGame.value = ApiResponse.success(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

     return responseGame
    }
}