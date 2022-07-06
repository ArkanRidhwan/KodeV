package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Nasa
import com.example.latihan13.vo.Resource

interface INasaUseCase {
    fun getNasa(): LiveData<Resource<List<Nasa>>>
}