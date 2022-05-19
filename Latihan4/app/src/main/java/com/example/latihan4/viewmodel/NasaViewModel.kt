package com.example.latihan4.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.latihan4.adapter.KartunAdapter
import com.example.latihan4.adapter.NasaAdapter
import com.example.latihan4.model.nasa.MainDataNasaItem
import com.example.latihan4.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NasaViewModel: ViewModel() {

    private lateinit var gambarNasaAdapter: NasaAdapter

    fun retrofitNasa(){
        val clientNasa= ServiceBuilder.apiServiceNasa.getNasa("Z2NZ6sys8NlgEf76YmxRkzullroaQgC2Cr1kmWyk", "50")

        clientNasa.enqueue(object : Callback<List<MainDataNasaItem>> {
            override fun onResponse(
                call: Call<List<MainDataNasaItem>>,
                response: Response<List<MainDataNasaItem>>
            ) {
                val listNasa = response.body()
                if(listNasa != null)
                    gambarNasaAdapter.setData(listNasa)
            }

            override fun onFailure(call: Call<List<MainDataNasaItem>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }
}