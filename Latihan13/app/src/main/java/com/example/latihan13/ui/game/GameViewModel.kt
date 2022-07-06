package com.example.latihan13.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan13.core.data.source.Repository
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.core.domain.usecase.IGameUseCase
import com.example.latihan13.vo.Resource

class GameViewModel(private val iGameUseCase: IGameUseCase) : ViewModel() {

    fun getGames(): LiveData<Resource<List<Game>>> = iGameUseCase.getGame()

    fun getFavoriteGame(): LiveData<List<Game>> = iGameUseCase.getFavoriteGame()

    fun updateGame(gameEntity: Game, newState: Boolean) =
        iGameUseCase.updateGame(gameEntity, newState)
}