package com.example.latihan121.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan121.data.source.Repository
import com.example.latihan121.data.source.local.entity.GameEntity
import com.example.latihan121.vo.Resource

class GameViewModel(private val gameRepository: Repository) : ViewModel() {

    fun getGames() : LiveData<Resource<PagedList<GameEntity>>> = gameRepository.getGame()

    fun getFavoriteGame(): LiveData<PagedList<GameEntity>> = gameRepository.getFavoriteGame()

    fun updateGame(gameEntity: GameEntity, newState: Boolean) = gameRepository.updateGame(gameEntity, newState)
}