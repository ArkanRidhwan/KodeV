package com.example.latihan15.core.di

import androidx.room.Room
import com.example.latihan15.core.data.Repository
import com.example.latihan15.core.data.local.LocalDataSource
import com.example.latihan15.core.data.local.room.MainDatabase
import com.example.latihan15.core.data.remote.RemoteDataSource
import com.example.latihan15.core.data.remote.api.ApiServiceMakeUp
import com.example.latihan15.core.data.remote.api.ApiServiceStory
import com.example.latihan15.core.domain.repository.IRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<MainDatabase>().mainDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDatabase::class.java,
            "Main.db"
        ).build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://makeup-api.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiServiceMakeUp::class.java)
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://story-api.dicoding.dev/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiServiceStory::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
    single <IRepository>{ Repository(get(), get()) }
}

