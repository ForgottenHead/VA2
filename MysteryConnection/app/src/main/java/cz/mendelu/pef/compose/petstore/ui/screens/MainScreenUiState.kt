package cz.mendelu.pef.compose.petstore.ui.screens

sealed class MainScreenUiState<out T> {
    class Start() : MainScreenUiState<Nothing>()
}
