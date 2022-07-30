package com.example.latihan14.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.latihan14.core.domain.usecase.IDestinasiUseCase

class MainViewModel(private val destinasiUseCase: IDestinasiUseCase) : ViewModel() {
    fun getDestinasi() = destinasiUseCase.getDestinasi().asLiveData()
}