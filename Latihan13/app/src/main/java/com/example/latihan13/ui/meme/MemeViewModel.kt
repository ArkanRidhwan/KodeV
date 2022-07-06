package com.example.latihan13.ui.meme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.core.domain.usecase.IMemeUseCase
import com.example.latihan13.vo.Resource

class MemeViewModel(private val iMemeUseCase: IMemeUseCase) : ViewModel() {
    fun getMeme(): LiveData<Resource<List<Meme>>> = iMemeUseCase.getMeme()
}