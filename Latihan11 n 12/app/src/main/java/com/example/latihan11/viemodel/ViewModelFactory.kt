package com.example.latihan11.viemodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan11.data.source.Repository
import com.example.latihan11.di.Injection
import com.example.latihan11.ui.games.GameViewModel
import com.example.latihan11.ui.kartun.KartunViewModel

class ViewModelFactory private constructor(
    private val repository: Repository
) :
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

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> return GameViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(KartunViewModel::class.java) -> return KartunViewModel(
                repository
            ) as T
            else -> throw Throwable("Unknown ViewModel Class:" + modelClass.name)
        }
    }
}