package cz.mendelu.pef.compose.mapapplication.di

import cz.mendelu.pef.compose.mapapplication.ui.screens.main.MainScreenViewModel
import cz.mendelu.pef.compose.mapapplication.ui.screens.markers.MarkerClusterViewModel
import cz.mendelu.pef.compose.mapapplication.ui.screens.polygons.PolygonAndPolylineViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainScreenViewModel() }
    viewModel { MarkerClusterViewModel(get()) }
    viewModel { PolygonAndPolylineViewModel() }

}
