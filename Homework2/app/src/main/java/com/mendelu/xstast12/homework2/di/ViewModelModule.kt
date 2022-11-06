package com.mendelu.xstast12.homework2.di

import com.mendelu.xstast12.homework2.ui.screens.MapScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MapScreenViewModel(get())
    }

}