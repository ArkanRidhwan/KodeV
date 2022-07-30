package com.example.latihan15.ui.dicoding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.latihan15.core.domain.usecase.IStoryUseCase

class StoryViewModel(private val iStoryUseCase: IStoryUseCase): ViewModel() {
    fun getStory() = iStoryUseCase.getStory().asLiveData()
}