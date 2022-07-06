package com.example.latihan13.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan13.core.domain.usecase.IGameUseCase
import com.example.latihan13.core.domain.usecase.IKartunUseCase
import com.example.latihan13.core.domain.usecase.IMemeUseCase
import com.example.latihan13.core.domain.usecase.INasaUseCase
import com.example.latihan13.di.Injection
import com.example.latihan13.ui.game.GameViewModel
import com.example.latihan13.ui.kartun.KartunViewModel
import com.example.latihan13.ui.meme.MemeViewModel
import com.example.latihan13.ui.nasa.NasaViewModel

class ViewModelFactory private constructor(
    private val iGameUseCase: IGameUseCase,
    private val iKartunUseCase: IKartunUseCase,
    private val iNasaUseCase: INasaUseCase,
    private val iMemeUseCase: IMemeUseCase
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideGameUseCase(context),
                    Injection.provideKartunUseCase(context),
                    Injection.provideNasaUseCase(context),
                    Injection.provideMemeUseCase(context)
                ).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> GameViewModel(
                iGameUseCase
            ) as T
            modelClass.isAssignableFrom(KartunViewModel::class.java) -> KartunViewModel(
                iKartunUseCase
            ) as T
            modelClass.isAssignableFrom(NasaViewModel::class.java) -> NasaViewModel(
                iNasaUseCase
            ) as T
            modelClass.isAssignableFrom(MemeViewModel::class.java) -> MemeViewModel(
                iMemeUseCase
            ) as T
            else -> throw  Throwable("Unknown ViewModel Class" + modelClass.name)
        }
    }
}