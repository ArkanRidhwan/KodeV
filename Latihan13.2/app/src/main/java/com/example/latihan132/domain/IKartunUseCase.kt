package com.example.latihan132.domain

import androidx.lifecycle.LiveData

interface IKartunUseCase {
    fun getKartun(): LiveData<List<KartunEntity>>
}