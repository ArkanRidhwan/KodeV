package com.example.latihan15.ui.dicoding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.latihan15.core.domain.usecase.IStoryUseCase
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryViewModel(private val iStoryUseCase: IStoryUseCase) : ViewModel() {
    fun getStory() = iStoryUseCase.getStory().asLiveData()
    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ) = iStoryUseCase.postStory(token, file, description).asLiveData()
}