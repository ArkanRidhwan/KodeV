package com.example.latihan5.data.source

import com.example.latihan5.data.source.remote.RemoteDataSource
import com.example.latihan5.data.source.remote.response.ResponseGame

class GameRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    GameDataSource {

    @Volatile
    private var instance: GameRepository? = null

    fun getInstance(remoteDataSource: RemoteDataSource): GameRepository =
        instance ?: synchronized(this) {
            instance ?: GameRepository(remoteDataSource).apply {
                instance = this
            }
        }

    override fun getGames(): ResponseGame {
        val response = remoteDataSource.getGames()
        return ResponseGame(response.results)
    }
}