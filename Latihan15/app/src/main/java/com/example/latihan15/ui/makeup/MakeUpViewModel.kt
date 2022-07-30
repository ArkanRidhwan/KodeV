package com.example.latihan15.ui.makeup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.latihan15.core.domain.usecase.IMakeupUseCase

class MakeUpViewModel(private val iMakeupUseCase: IMakeupUseCase) : ViewModel() {
    fun getMakeup() = iMakeupUseCase.getMakeup().asLiveData()
}