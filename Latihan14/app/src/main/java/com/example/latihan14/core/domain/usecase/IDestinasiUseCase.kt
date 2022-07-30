package com.example.latihan14.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan14.core.data.source.Resource
import com.example.latihan14.core.domain.model.Destinasi
import kotlinx.coroutines.flow.Flow

interface IDestinasiUseCase {
    fun getDestinasi():Flow<Resource<List<Destinasi>>>
}