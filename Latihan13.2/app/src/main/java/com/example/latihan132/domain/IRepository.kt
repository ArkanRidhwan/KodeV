package com.example.latihan132.domain

import androidx.lifecycle.LiveData

interface IRepository {
    fun getKartun(): LiveData<List<KartunEntity>>
}