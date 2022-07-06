package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Nasa
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.vo.Resource

class NasaInteractor(private val iNasaRepository: IRepository): INasaUseCase {
    override fun getNasa(): LiveData<Resource<List<Nasa>>> {
        return iNasaRepository.getNasa()
    }
}