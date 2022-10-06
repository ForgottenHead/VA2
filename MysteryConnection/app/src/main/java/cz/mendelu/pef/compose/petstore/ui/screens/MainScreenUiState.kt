package cz.mendelu.pef.compose.petstore.ui.screens

sealed class MainScreenUiState<out T> {
    class Start() : MainScreenUiState<Nothing>()
    class Error(var error: Int): MainScreenUiState<Nothing>()
    class Success<T>(var data: T): MainScreenUiState<T>()
}
