package com.example.latihan11.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.data.source.local.entity.KartunEntity
import com.example.latihan11.vo.Resource

interface DataSource {

    //Game
    fun getGames(): LiveData<Resource<PagedList<GameEntity>>>
    fun getFavoritesGames(): LiveData<Resource<PagedList<GameEntity>>>
    fun updateGame(game: GameEntity, newState: Boolean)

    //Kartun
    fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>>
}