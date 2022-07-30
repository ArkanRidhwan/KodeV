package com.example.latihan15.core.data

import com.example.latihan15.core.data.local.LocalDataSource
import com.example.latihan15.core.data.remote.ApiResponse
import com.example.latihan15.core.data.remote.RemoteDataSource
import com.example.latihan15.core.data.remote.response.dicoding.getStory.ResponseStory
import com.example.latihan15.core.data.remote.response.makeup.ResponseItem
import com.example.latihan15.core.domain.model.Makeup
import com.example.latihan15.core.domain.model.Story
import com.example.latihan15.core.domain.repository.IRepository
import com.example.latihan15.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData).apply {
                    instance = this
                }
            }
    }

    override fun getMakeup(): Flow<Resource<List<Makeup>>> {
        return object : NetworkBoundResource<List<Makeup>, List<ResponseItem>>() {
            override fun loadFromDB(): Flow<List<Makeup>> {
                return localDataSource.getLocalMakeup().map {
                    DataMapper.mapMakeUpEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Makeup>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseItem>>> {
                return remoteDataSource.getMakeup()
            }

            override suspend fun saveCallResult(data: List<ResponseItem>) {
                val listData = DataMapper.mapResponseToMakeUpEntities(data)
                localDataSource.insertMakeup(listData)
            }
        }.asFlow()
    }

    override fun getStory(): Flow<Resource<List<Story>>> {
        return object : NetworkBoundResource<List<Story>, ResponseStory>() {
            override fun loadFromDB(): Flow<List<Story>> {
                return localDataSource.getLocalStory().map {
                    DataMapper.mapStoryEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Story>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseStory>> {
                return remoteDataSource.getStory()
            }

            override suspend fun saveCallResult(data: ResponseStory) {
                val listData = DataMapper.mapResponeToStoryEntities(data)
                localDataSource.insertStory(listData)
            }
        }.asFlow()
    }
}