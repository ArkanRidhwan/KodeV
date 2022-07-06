package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.vo.Resource

class MemeInteractor(private val iMemeIRepository: IRepository): IMemeUseCase {
    override fun getMeme(): LiveData<Resource<List<Meme>>> {
        return iMemeIRepository.getMeme()
    }
}