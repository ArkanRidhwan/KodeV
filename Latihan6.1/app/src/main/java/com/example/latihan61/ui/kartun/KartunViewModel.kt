package com.example.latihan61.ui.kartun

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.data.remote.response.kartun.KartunResponse
import com.example.latihan61.data.remote.response.kartun.Result
import com.example.latihan61.repository.Repository

class KartunViewModel(private val kartunRepository: Repository) : ViewModel() {
    fun getKartuns() : LiveData<KartunResponse> = kartunRepository.getCharacters()
}