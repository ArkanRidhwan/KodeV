package com.example.latihan15.core.domain.repository

import com.example.latihan15.core.data.Resource
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getMakeup(): Flow<Resource<List<Makeup>>>
    fun getStory(): Flow<Resource<List<Story>>>
}