package com.example.latihan121.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.latihan121.data.source.local.entity.GameEntity
import com.example.latihan121.data.source.local.entity.KartunEntity
import com.example.latihan121.data.source.local.entity.NasaEntity
import com.example.latihan121.vo.Resource

interface DataSource {

    //Game
    fun getGame(): LiveData<Resource<PagedList<GameEntity>>>
    fun getFavoriteGame(): LiveData<PagedList<GameEntity>>
    fun updateGame(game: GameEntity, newState: Boolean)

    //Kartun
    fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>>
    fun getFavoriteKartun(): LiveData<Resource<PagedList<KartunEntity>>>
    fun updateKartun(kartun: KartunEntity, newState: Boolean)

    //Nasa
    fun getNasa(): LiveData<Resource<List<NasaEntity>>>
    fun getFavoriteNasa(): LiveData<Resource<PagedList<NasaEntity>>>
    fun updateNasa(nasa: NasaEntity, newState: Boolean)
}