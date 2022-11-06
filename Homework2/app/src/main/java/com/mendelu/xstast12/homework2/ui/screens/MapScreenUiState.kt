package com.mendelu.xstast12.homework2.ui.screens

sealed class MapScreenUiState<out T>{
    object Start : MapScreenUiState<Nothing>()
    class Success<T>(var data: T) : MapScreenUiState<T>()
    class Error(var error: Int) : MapScreenUiState<Nothing>()
}
