package com.example.latihan15.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan15.core.di.Injection
import com.example.latihan15.core.domain.usecase.IMakeupUseCase
import com.example.latihan15.core.domain.usecase.IStoryUseCase
import com.example.latihan15.ui.dicoding.StoryViewModel
import com.example.latihan15.ui.makeup.MakeUpViewModel

class ViewModelFactory(private val iMakeupUseCase: IMakeupUseCase, private val iStoryUseCase: IStoryUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMakeupUseCase(context), Injection.provideStoryUseCase(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MakeUpViewModel::class.java) -> MakeUpViewModel(
                iMakeupUseCase
            ) as T
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> StoryViewModel(
                iStoryUseCase
            ) as T
            else -> {
                throw  Throwable("Unknown ViewModel Class" + modelClass.name)
            }
        }
    }
}