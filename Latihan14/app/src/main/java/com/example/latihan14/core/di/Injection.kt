package com.example.latihan14.core.di

import android.content.Context
import com.example.latihan14.core.data.source.Repository
import com.example.latihan14.core.data.source.local.LocalDataSource
import com.example.latihan14.core.data.source.local.room.DestinasiDatabase
import com.example.latihan14.core.data.source.remote.RemoteDataSource
import com.example.latihan14.core.domain.repository.IRepository
import com.example.latihan14.core.domain.usecase.DestinasiInteractor
import com.example.latihan14.core.domain.usecase.IDestinasiUseCase
import com.example.latihan14.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IRepository {
        val database = DestinasiDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance2(database.destinasiDao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideKartunUseCase(context: Context): IDestinasiUseCase{
        val repository = provideRepository(context)
        return DestinasiInteractor(repository)
    }
}