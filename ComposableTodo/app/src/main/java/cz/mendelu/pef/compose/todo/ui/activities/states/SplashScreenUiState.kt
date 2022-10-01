package cz.mendelu.pef.compose.todo.ui.activities.states

sealed class SplashScreenUiState {
    object Default : SplashScreenUiState()
    object RunForAFirstTime : SplashScreenUiState()
    object ContinueToApp : SplashScreenUiState()
}