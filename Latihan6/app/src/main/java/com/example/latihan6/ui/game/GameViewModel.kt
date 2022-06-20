package com.example.latihan6.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan6.data.source.GameRepository
import com.kodev.games.data.source.remote.response.ResponseGame

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun getGames() : LiveData<ResponseGame> = gameRepository.getGames()
}