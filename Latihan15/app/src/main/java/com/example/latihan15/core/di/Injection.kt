package com.example.latihan15.core.di

import android.content.Context
import com.example.latihan15.core.data.Repository
import com.example.latihan15.core.data.local.LocalDataSource
import com.example.latihan15.core.data.local.room.MainDatabase
import com.example.latihan15.core.data.remote.RemoteDataSource
import com.example.latihan15.core.domain.repository.IRepository
import com.example.latihan15.core.domain.usecase.IMakeupUseCase
import com.example.latihan15.core.domain.usecase.IStoryUseCase
import com.example.latihan15.core.domain.usecase.MakeupInteractor
import com.example.latihan15.core.domain.usecase.StoryInteractor

object Injection {

    fun provideRepository(context: Context): IRepository {
        val database = MainDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.mainDao())
        return Repository.getInstance(remoteDataSource, localDataSource)
    }

    fun provideMakeupUseCase(context: Context): IMakeupUseCase {
        val repository = provideRepository(context)
        return MakeupInteractor(repository)
    }

    fun provideStoryUseCase(context: Context): IStoryUseCase {
        val repository = provideRepository(context)
        return StoryInteractor(repository)
    }
}