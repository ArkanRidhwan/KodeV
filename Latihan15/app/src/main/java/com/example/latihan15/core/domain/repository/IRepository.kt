package com.example.latihan15.core.domain.repository

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.data.remote.response.dicoding.getStory.ResponseStory
import com.example.latihan15.core.data.remote.response.dicoding.postStory.ResponsePostStory
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.core.domain.model.Story
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IRepository {
    fun getMakeup(): Flow<Resource<List<Makeup>>>
    fun getStory(): Flow<Resource<List<Story>>>
    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
    ): Flow<Resource<ResponsePostStory>>
}