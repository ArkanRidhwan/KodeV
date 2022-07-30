package com.example.latihan14.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.latihan14.core.data.source.local.LocalDataSource
import com.example.latihan14.core.data.source.remote.ApiResponse
import com.example.latihan14.core.data.source.remote.RemoteDataSource
import com.example.latihan14.core.data.source.remote.response.Response
import com.example.latihan14.core.domain.model.Destinasi
import com.example.latihan14.core.domain.repository.IRepository
import com.example.latihan14.utils.AppExecutors
import com.example.latihan14.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getDestinasi(): Flow<Resource<List<Destinasi>>> {
        return object : NetworkBoundResource<List<Destinasi>, Response>(appExecutors) {
            override fun loadFromDB(): Flow<List<Destinasi>> {
                return localDataSource.getLocalDestinasi().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
                /*return Transformations.map(localDataSource.getLocalDestinasi()){
                    DataMapper.mapEntitiesToDomain(it)
                }*/
            }

            override fun shouldFetch(data: List<Destinasi>?): Boolean {
                return data == null || data.isEmpty()
            }

             override suspend fun createCall(): Flow<ApiResponse<Response>> {
                return remoteDataSource.getDestinasi()
            }

            override suspend fun saveCallResult(data: Response) {
                val listDestinasi = DataMapper.mapResponsetoEntities(data)
                localDataSource.insertDestinasi(listDestinasi)
            }
        }.asFlow()
    }
}