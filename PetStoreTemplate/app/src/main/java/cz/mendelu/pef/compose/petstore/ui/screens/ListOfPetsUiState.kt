package cz.mendelu.pef.compose.petstore.ui.screens

sealed class ListOfPetsUiState<out T> {
    class Start() : ListOfPetsUiState<Nothing>()
    class Success<T>(var data: T) : ListOfPetsUiState<T>()
    class Error(var error: Int) : ListOfPetsUiState<Nothing>()
}
