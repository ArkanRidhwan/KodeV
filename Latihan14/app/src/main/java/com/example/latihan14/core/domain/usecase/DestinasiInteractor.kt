package com.example.latihan14.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan14.core.data.source.Resource
import com.example.latihan14.core.domain.model.Destinasi
import com.example.latihan14.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class DestinasiInteractor(private val iDestinasiRepository: IRepository): IDestinasiUseCase {
    override fun getDestinasi(): Flow<Resource<List<Destinasi>>> {
        return iDestinasiRepository.getDestinasi()
    }
}