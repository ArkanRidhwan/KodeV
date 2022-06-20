package com.example.latihan6.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan6.data.di.Injection
import com.example.latihan6.data.source.GameRepository
import com.example.latihan6.ui.game.GameViewModel

class ViewModelFactory private constructor(private val gameRepository: GameRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> {
                return GameViewModel(gameRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class:" + modelClass.name)
        }
    }
}