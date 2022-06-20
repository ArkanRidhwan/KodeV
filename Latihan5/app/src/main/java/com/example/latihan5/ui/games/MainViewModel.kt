package com.example.latihan5.ui.games

import androidx.lifecycle.ViewModel
import com.example.latihan5.data.source.GameRepository

class MainViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun getGames() = gameRepository.getGames()
}