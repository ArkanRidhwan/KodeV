package com.example.latihan11.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan11.data.source.remote.api.ApiConfig.getApiServiceGame
import com.example.latihan11.data.source.remote.api.ApiConfig.getApiServiceKartun
import com.example.latihan11.data.source.remote.response.game.ResponseGame
import com.example.latihan11.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan11.vo.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getGames(): LiveData<ApiResponse<ResponseGame>> {
        val responseGame = MutableLiveData<ApiResponse<ResponseGame>>()

        val client = getApiServiceGame().getGames("d084045ca6164bbeb97021752a930416", "20")
        client.enqueue(object : Callback<ResponseGame> {
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseGame.value = ApiResponse.success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
        return responseGame
    }

    fun getKartun(): LiveData<ApiResponse<ResponseKartun>> {
        val responseKartun = MutableLiveData<ApiResponse<ResponseKartun>>()

        val client = getApiServiceKartun().getKartun()
        client.enqueue(object : Callback<ResponseKartun> {
            override fun onResponse(
                call: Call<ResponseKartun>,
                response: Response<ResponseKartun>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseKartun.value = ApiResponse.success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseKartun>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        return responseKartun
    }
}