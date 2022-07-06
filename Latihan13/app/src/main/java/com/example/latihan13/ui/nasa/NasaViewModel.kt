package com.example.latihan13.ui.nasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan13.core.data.source.Repository
import com.example.latihan13.core.data.source.local.entity.NasaEntity
import com.example.latihan13.core.domain.model.Nasa
import com.example.latihan13.core.domain.usecase.INasaUseCase
import com.example.latihan13.vo.Resource

class NasaViewModel(private val iNasaUseCase: INasaUseCase): ViewModel() {

    fun getNasa(): LiveData<Resource<List<Nasa>>> = iNasaUseCase.getNasa()
}