package com.example.latihan132.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan132.domain.IKartunUseCase
import com.example.latihan132.domain.KartunEntity

class MainViewModel(private val iKartunUseCase: IKartunUseCase) : ViewModel() {

    fun getKartun(): LiveData<List<KartunEntity>> = iKartunUseCase.getKartun()
}