package com.example.latihan13.di

import android.content.Context
import com.example.latihan13.core.data.source.Repository
import com.example.latihan13.core.data.source.local.LocalDataSource
import com.example.latihan13.core.data.source.local.entity.PrimaryDatabase
import com.example.latihan13.core.data.source.remote.response.RemoteDataSource
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.core.domain.usecase.*
import com.example.latihan13.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): IRepository {
        val database = PrimaryDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }


    fun provideGameUseCase(context: Context): IGameUseCase{
        val repository = provideRepository(context)
        return GameInteractor(repository)
    }

    fun provideKartunUseCase(context: Context): IKartunUseCase{
        val repository = provideRepository(context)
        return KartunInteractor(repository)
    }

    fun provideNasaUseCase(context: Context): INasaUseCase{
        val repository = provideRepository(context)
        return NasaInteractor(repository)
    }

    fun provideMemeUseCase(context: Context): IMemeUseCase{
        val repository = provideRepository(context)
        return MemeInteractor(repository)
    }
}