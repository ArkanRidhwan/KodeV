package com.example.latihan13.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.core.domain.model.Nasa
import com.example.latihan13.vo.Resource

interface IRepository {
    fun getGame(): LiveData<Resource<List<Game>>>
    fun getFavoriteGame(): LiveData<List<Game>>
    fun updateGame(game: Game, newSate: Boolean)

    fun getKartun(): LiveData<Resource<List<Kartun>>>
    fun getFavoriteKartun(): LiveData<Resource<List<Kartun>>>
    fun updateKartun(kartun: Kartun, newState: Boolean)

    fun getNasa(): LiveData<Resource<List<Nasa>>>
    fun getFavoriteNasa(): LiveData<Resource<List<Nasa>>>
    fun updateNasa(nasa: Nasa, newState: Boolean)

    fun getMeme(): LiveData<Resource<List<Meme>>>
}