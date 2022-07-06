package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.vo.Resource

interface IMemeUseCase {
    fun getMeme(): LiveData<Resource<List<Meme>>>
}