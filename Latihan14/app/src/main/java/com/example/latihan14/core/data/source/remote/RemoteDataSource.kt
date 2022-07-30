package com.example.latihan14.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan14.core.data.source.remote.api.ApiConfig
import com.example.latihan14.core.data.source.remote.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    /*fun getDestinasi(): LiveData<ApiResponse<Response>> {
        val responseDestinasi = MutableLiveData<ApiResponse<Response>>()
        val client = ApiConfig.getApiService().getDestinasi()
        client.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseDestinasi.value = ApiResponse.Success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
        return responseDestinasi
    }*/

     suspend fun getDestinasi(): Flow<ApiResponse<Response>> {
        return flow {
            try {
                val responseDestinasi = ApiConfig.getApiService().getDestinasi()
                if (responseDestinasi.places.isNotEmpty()) {
                    emit(ApiResponse.Success(responseDestinasi))
                } else {
                    ApiResponse.Empty
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getDestinasi: $e", )
            }
        }.flowOn(Dispatchers.IO)
    }
}