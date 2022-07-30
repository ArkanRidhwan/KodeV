package com.example.latihan131.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan131.domain.usecases.IKartunUseCase
import com.example.latihan131.presentation.ui.KartunViewModel

class ViewModelFactory private constructor(
    private val iKartunUseCase: IKartunUseCase
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(KartunViewModel::class.java) -> KartunViewModel(
                iKartunUseCase
            ) as T
            else -> throw  Throwable("Unknown View Model Class" + modelClass.name)
        }
    }
}