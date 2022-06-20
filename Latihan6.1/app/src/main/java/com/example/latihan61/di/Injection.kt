package com.example.latihan61.di

import android.content.Context
import com.example.latihan61.data.remote.RemoteRepository
import com.example.latihan61.repository.Repository


object Injection {

    fun provideRepository(context: Context): Repository{
        val remoteDataSource = RemoteRepository.getInstance()
        return Repository.getInstance(remoteDataSource)
    }

}