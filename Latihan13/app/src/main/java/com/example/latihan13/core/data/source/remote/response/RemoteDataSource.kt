package com.example.latihan13.core.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan13.core.data.source.remote.api.ApiConfig
import com.example.latihan13.core.data.source.remote.response.game.ResponseGame
import com.example.latihan13.core.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan13.core.data.source.remote.response.meme.ResponseMeme
import com.example.latihan13.core.data.source.remote.response.nasa.ResponseNasaItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getGame(): LiveData<ApiResponse<ResponseGame>> {
        val responseGame = MutableLiveData<ApiResponse<ResponseGame>>()
        val client = ApiConfig.getApiServiceGame().getGames("d084045ca6164bbeb97021752a930416")
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
        val client = ApiConfig.getApiServiceKartun().getKartun()
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

    fun getNasa(): LiveData<ApiResponse<List<ResponseNasaItem>>> {
        val responseNasa = MutableLiveData<ApiResponse<List<ResponseNasaItem>>>()
        val client =
            ApiConfig.getApiServiceNasa().getNasa("Z2NZ6sys8NlgEf76YmxRkzullroaQgC2Cr1kmWyk", 20)
        client.enqueue(object : Callback<List<ResponseNasaItem>> {
            override fun onResponse(
                call: Call<List<ResponseNasaItem>>,
                response: Response<List<ResponseNasaItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseNasa.value = ApiResponse.success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseNasaItem>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        return responseNasa
    }

    fun getMeme(): LiveData<ApiResponse<ResponseMeme>> {
        val responseMeme = MutableLiveData<ApiResponse<ResponseMeme>>()
        val client = ApiConfig.getApiServiceMeme().getMeme()
        client.enqueue(object : Callback<ResponseMeme> {
            override fun onResponse(call: Call<ResponseMeme>, response: Response<ResponseMeme>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseMeme.value = ApiResponse.success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMeme>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
        return responseMeme
    }
}