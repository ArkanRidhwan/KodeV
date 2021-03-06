package com.example.latihan5.data.source.remote

import com.example.latihan5.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(helper).apply {
                instance = this
            }
        }
    }
    fun getGames() = jsonHelper.loadGame()
}