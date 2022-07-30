package com.example.latihan132.domain

import androidx.lifecycle.LiveData

class KartunInteractor(private val iKartunIRepository: IRepository): IKartunUseCase {
    override fun getKartun(): LiveData<List<KartunEntity>> {
        return iKartunIRepository.getKartun()
    }
}