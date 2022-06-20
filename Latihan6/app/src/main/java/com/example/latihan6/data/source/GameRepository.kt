package com.example.latihan6.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan6.data.source.remote.response.RemoteDataSource
import com.kodev.games.data.source.remote.response.ResponseGame

class GameRepository private constructor(private val remoteDataSource: RemoteDataSource) {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteDataSource).apply {
                    instance = this
                }
            }
    }

    fun getGames(): LiveData<ResponseGame>{
        val getGames = MutableLiveData<ResponseGame>()
        remoteDataSource.getGames(object : RemoteDataSource.LoadGetGames{
            override fun onLoadGetGames(listGames: ResponseGame) {
                getGames.value = listGames
            }

        })
        return getGames
    }
}