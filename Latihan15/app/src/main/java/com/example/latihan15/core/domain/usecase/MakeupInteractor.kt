package com.example.latihan15.core.domain.usecase

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class MakeupInteractor(private val iRepository: IRepository) : IMakeupUseCase {
    override fun getMakeup(): Flow<Resource<List<Makeup>>> {
        return iRepository.getMakeup()
    }

}