package com.example.latihan13.ui.kartun

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.core.domain.usecase.IKartunUseCase
import com.example.latihan13.vo.Resource

class KartunViewModel(private val iKartunUseCase: IKartunUseCase) : ViewModel() {

    fun getKartun(): LiveData<Resource<List<Kartun>>> = iKartunUseCase.getKartun()
}