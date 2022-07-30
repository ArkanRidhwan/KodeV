package com.example.latihan15.core.domain.usecase

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.domain.model.Makeup
import kotlinx.coroutines.flow.Flow

interface IMakeupUseCase {
    fun getMakeup(): Flow<Resource<List<Makeup>>>
}