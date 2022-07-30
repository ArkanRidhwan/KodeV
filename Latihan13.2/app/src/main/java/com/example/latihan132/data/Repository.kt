package com.example.latihan132.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.latihan132.data.remote.RemoteDataSource
import com.example.latihan132.domain.IRepository
import com.example.latihan132.domain.KartunEntity
import com.example.latihan132.utils.DataMapper

class Repository(private val remoteDataSource: RemoteDataSource) : IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(
                    remoteDataSource
                ).apply {
                    instance = this
                }
            }
    }

    override fun getKartun(): LiveData<List<KartunEntity>> {
        return Transformations.map(remoteDataSource.getKartun()) {
            DataMapper.mapResponseToDomain(it)
        }
    }
}