package com.example.latihan131.domain.usecases

import androidx.lifecycle.LiveData
import com.example.latihan131.domain.model.Kartun
import com.example.latihan131.domain.repository.IRepository

class KartunInteractor(private val iKartunIRepository: IRepository) : IKartunUseCase {
    override fun createCall(): LiveData<List<Kartun>> {
        return iKartunIRepository.createCall()
    }
}