package com.example.latihan131.data.repository

import androidx.lifecycle.LiveData
import com.example.latihan131.data.datasource.local.LocalDataSource
import com.example.latihan131.data.datasource.remote.RemoteDataSource
import com.example.latihan131.data.datasource.remote.response.kartun.ResponseKartun
import com.example.latihan131.domain.repository.IRepository

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRepository {

    override fun createCall(): LiveData<ResponseKartun> {
        return remoteDataSource.getKartun()
    }
}