package com.example.latihan11.di

import android.content.Context
import com.example.latihan11.data.source.Repository
import com.example.latihan11.data.source.local.LocalDataSource
import com.example.latihan11.data.source.local.entity.DatabaseUtama
import com.example.latihan11.data.source.remote.RemoteDataSource
import com.example.latihan11.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): Repository{
        val database = DatabaseUtama.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}