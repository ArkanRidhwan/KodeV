package com.example.latihan15.core.domain.usecase

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.data.remote.response.dicoding.postStory.ResponsePostStory
import com.example.latihan15.core.domain.model.Story
import com.example.latihan15.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryInteractor(private val iRepository: IRepository) : IStoryUseCase {
    override fun getStory(): Flow<Resource<List<Story>>> {
        return iRepository.getStory()
    }

    override fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ): Flow<Resource<ResponsePostStory>> {
        return iRepository.postStory(token, file, description)
    }
}