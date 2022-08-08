package com.example.latihan15.core.di

import com.example.latihan15.core.domain.usecase.IMakeupUseCase
import com.example.latihan15.core.domain.usecase.IStoryUseCase
import com.example.latihan15.core.domain.usecase.MakeupInteractor
import com.example.latihan15.core.domain.usecase.StoryInteractor
import com.example.latihan15.ui.dicoding.StoryViewModel
import com.example.latihan15.ui.makeup.MakeUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IMakeupUseCase> { MakeupInteractor(get()) }
    factory<IStoryUseCase> { StoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { (MakeUpViewModel(get())) }
    viewModel { StoryViewModel(get()) }
}