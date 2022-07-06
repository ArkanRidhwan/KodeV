package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.vo.Resource

class KartunInteractor(private val iKartunRepository: IRepository): IKartunUseCase {
    override fun getKartun(): LiveData<Resource<List<Kartun>>> {
        return iKartunRepository.getKartun()
    }
}