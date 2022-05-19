package com.example.latihan4.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.latihan4.adapter.KartunAdapter
import com.example.latihan4.model.kartun.MainDataKartun
import com.example.latihan4.service.ServiceBuilder.apiServiceKartun
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KartunViewModel: ViewModel() {

    private lateinit var gambarKartunAdapter: KartunAdapter

    fun retrofitKartun(){
        val clientKartun = apiServiceKartun.getKartun()

        clientKartun.enqueue(object: Callback<MainDataKartun>{
            override fun onResponse(
                call: Call<MainDataKartun>,
                response: Response<MainDataKartun>
            ) {
                val listKartun = response.body()?.results
                if(listKartun != null)
                    gambarKartunAdapter.setData(listKartun)
            }

            override fun onFailure(call: Call<MainDataKartun>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }
}