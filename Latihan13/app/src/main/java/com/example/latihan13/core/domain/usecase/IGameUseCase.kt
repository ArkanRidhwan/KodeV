package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.vo.Resource

interface IGameUseCase {
    fun getGame(): LiveData<Resource<List<Game>>>
    fun getFavoriteGame(): LiveData<List<Game>>
    fun updateGame(game: Game, newState: Boolean)
}