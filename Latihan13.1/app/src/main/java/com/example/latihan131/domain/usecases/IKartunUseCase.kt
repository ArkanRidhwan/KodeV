package com.example.latihan131.domain.usecases

import androidx.lifecycle.LiveData
import com.example.latihan131.domain.model.Kartun

interface IKartunUseCase {
    fun createCall(): LiveData<List<Kartun>>
}