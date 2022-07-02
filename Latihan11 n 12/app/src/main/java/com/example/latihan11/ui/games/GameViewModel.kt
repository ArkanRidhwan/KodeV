package com.example.latihan11.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan11.data.source.Repository
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.vo.Resource

class GameViewModel(private val gameRepository: Repository): ViewModel() {

    fun getGames(): LiveData<Resource<PagedList<GameEntity>>> = gameRepository.getGames()

    fun getFavoriteGame(): LiveData<PagedList<GameEntity>> = gameRepository.getFavoritesGames()

    fun updateGame(gameEntity: GameEntity, newState: Boolean) = gameRepository.updateGame(gameEntity, newState)


}