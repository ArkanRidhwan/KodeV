package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.vo.Resource

interface IKartunUseCase {
    fun getKartun(): LiveData<Resource<List<Kartun>>>
}