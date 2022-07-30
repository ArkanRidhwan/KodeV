package com.example.latihan132.di

import android.content.Context
import com.example.latihan132.data.Repository
import com.example.latihan132.data.remote.RemoteDataSource
import com.example.latihan132.domain.IKartunUseCase
import com.example.latihan132.domain.IRepository
import com.example.latihan132.domain.KartunInteractor

object Injection {

    fun provideRepository(context: Context): IRepository {
        val remoteDataSource = RemoteDataSource()
        return Repository.getInstance(remoteDataSource)
    }

    fun provideKartunUseCase(context: Context): IKartunUseCase{
        val repository = provideRepository(context)
        return KartunInteractor(repository)
    }
}