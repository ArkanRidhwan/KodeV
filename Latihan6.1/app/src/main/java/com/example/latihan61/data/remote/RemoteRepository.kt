package com.example.latihan61.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.example.latihan61.data.remote.response.ApiConfig.getApiServiceHome
import com.example.latihan61.data.remote.response.ApiConfig.getApiServiceKartun
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.data.remote.response.kartun.KartunResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {
    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository = instance ?: RemoteRepository().apply {
            instance = this
        }
    }

    fun getHome(callback: LoadGetHome) {
        val client = getApiServiceHome().getUsers()
        client.enqueue(object : Callback<List<ResponseHomeItem>> {
            override fun onResponse(
                call: Call<List<ResponseHomeItem>>,
                response: Response<List<ResponseHomeItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onLoadGetUsers(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<ResponseHomeItem>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })

    }

    interface LoadGetHome {
        fun onLoadGetUsers(listUser: List<ResponseHomeItem>)
    }

    fun getKartuns(callback: LoadGetKartun) {
        val client = getApiServiceKartun().getCharacters()
        client.enqueue(object :Callback<KartunResponse>{
            override fun onResponse(
                call: Call<KartunResponse>,
                response: Response<KartunResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.onLoadGetCharacters(it)
                    }
                }
            }

            override fun onFailure(call: Call<KartunResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }

    interface LoadGetKartun {
        fun onLoadGetCharacters(listCharacters: KartunResponse)
    }
}