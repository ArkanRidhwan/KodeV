package com.example.latihan131.data.datasource.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan131.data.datasource.remote.api.ApiConfig
import com.example.latihan131.data.datasource.remote.response.kartun.ResponseKartun
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getKartun(): LiveData<ResponseKartun> {
        val responseKartun = MutableLiveData<ResponseKartun>()
        val client = ApiConfig.getApiServiceKartun().getKartun()
        client.enqueue(object : Callback<ResponseKartun> {
            override fun onResponse(
                call: Call<ResponseKartun>,
                response: Response<ResponseKartun>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseKartun.value
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