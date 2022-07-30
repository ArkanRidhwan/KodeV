package com.example.latihan15.core.domain.usecase

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.domain.model.Story
import com.example.latihan15.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class StoryInteractor(private val iRepository: IRepository) : IStoryUseCase {
    override fun getStory(): Flow<Resource<List<Story>>> {
        return iRepository.getStory()
    }
}