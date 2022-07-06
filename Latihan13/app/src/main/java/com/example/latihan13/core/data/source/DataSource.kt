package com.example.latihan13.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.latihan13.core.data.source.local.entity.GameEntity
import com.example.latihan13.core.data.source.local.entity.KartunEntity
import com.example.latihan13.core.data.source.local.entity.NasaEntity
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.vo.Resource

interface DataSource {

    //Game
    fun getGame(): LiveData<Resource<PagedList<Game>>>
    fun getFavoriteGame(): LiveData<PagedList<Game>>
    fun updateGame(game: Game, newState: Boolean)

    //Kartun
    fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>>
    fun getFavoriteKartun(): LiveData<Resource<PagedList<KartunEntity>>>
    fun updateKartun(kartun: KartunEntity, newState: Boolean)

    //Nasa
    fun getNasa(): LiveData<Resource<List<NasaEntity>>>
    fun getFavoriteNasa(): LiveData<Resource<PagedList<NasaEntity>>>
    fun updateNasa(nasa: NasaEntity, newState: Boolean)
}