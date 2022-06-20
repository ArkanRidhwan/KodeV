package com.example.latihan61.data

import androidx.lifecycle.LiveData
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.data.remote.response.kartun.KartunResponse

interface DataSource {

    fun getUsers(): LiveData<List<ResponseHomeItem>>
    fun getCharacters(): LiveData<KartunResponse>
}