package com.example.latihan3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.latihan3.adapter.KartunAdapter
import com.example.latihan3.adapter.NasaAdapter
import com.example.latihan3.databinding.ActivityMainBinding
import com.example.latihan3.model.gambarKartun.MainDataKartun
import com.example.latihan3.model.gambarNasa.MainDataNasaItem
import com.example.latihan3.service.ServiceBuilder.apiServiceKartun
import com.example.latihan3.service.ServiceBuilder.apiServiceNasa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gambarKartunAdapter: KartunAdapter
    private lateinit var gambarNasaAdapter: NasaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gambarKartunAdapter = KartunAdapter()
        gambarNasaAdapter =  NasaAdapter()
        binding.apply {
            rvGambarKartun.adapter = gambarKartunAdapter
            rvGambarNasa.adapter = gambarNasaAdapter
        }

        val clientKartun = apiServiceKartun.getListCharacter()
        val clientNasa = apiServiceNasa.getListNasa("Z2NZ6sys8NlgEf76YmxRkzullroaQgC2Cr1kmWyk", "50")

        clientKartun.enqueue(object : Callback<MainDataKartun>{
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

        clientNasa.enqueue(object : Callback<List<MainDataNasaItem>>{
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
    
    companion object {
        private const val TAG = "MainActivity"
    }
}