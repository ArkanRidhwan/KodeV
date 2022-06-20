package com.example.latihan5.data.source

import com.example.latihan5.data.source.remote.response.ResponseGame

interface GameDataSource {
    fun getGames(): ResponseGame
}