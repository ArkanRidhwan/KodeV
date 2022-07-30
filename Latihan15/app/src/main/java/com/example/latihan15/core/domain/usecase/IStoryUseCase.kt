package com.example.latihan15.core.domain.usecase

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface IStoryUseCase {
    fun getStory(): Flow<Resource<List<Story>>>
}