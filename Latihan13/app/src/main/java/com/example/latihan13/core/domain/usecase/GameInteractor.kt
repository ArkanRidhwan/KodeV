package com.example.latihan13.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.vo.Resource

class GameInteractor(private val iGameRepository: IRepository): IGameUseCase{
    override fun getGame(): LiveData<Resource<List<Game>>> {
        return iGameRepository.getGame()
    }

    override fun getFavoriteGame(): LiveData<List<Game>> {
        return iGameRepository.getFavoriteGame()
    }

    override fun updateGame(game: Game, newState: Boolean) {
        return iGameRepository.updateGame(game, newState)
    }
}