package com.example.latihan6.data.di

import android.content.Context
import com.example.latihan6.data.source.GameRepository
import com.example.latihan6.data.source.remote.response.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): GameRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return GameRepository.getInstance(remoteDataSource)
    }

}