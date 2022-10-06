package cz.mendelu.pef.compose.petstore.di

import cz.mendelu.pef.compose.petstore.ui.screens.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainScreenViewModel(get())
    }
}