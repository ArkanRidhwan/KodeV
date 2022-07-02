package com.example.latihan121.di

import android.content.Context
import com.example.latihan121.data.source.Repository
import com.example.latihan121.data.source.local.LocalDataSource
import com.example.latihan121.data.source.local.entity.PrimaryDatabase
import com.example.latihan121.data.source.remote.response.RemoteDataSource
import com.example.latihan121.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): Repository {
        val database = PrimaryDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}