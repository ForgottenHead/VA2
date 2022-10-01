package cz.mendelu.pef.compose.todo.ui.activities.viewmodels

import cz.mendelu.pef.compose.todo.architecture.BaseViewModel
import cz.mendelu.pef.compose.todo.datastore.IDataStoreRepository
import cz.mendelu.pef.compose.todo.ui.activities.states.SplashScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenActivityViewModel(
    private val dataStoreRepository: IDataStoreRepository) : BaseViewModel() {

    private val _splashScreenState = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Default)
    val splashScreenState: StateFlow<SplashScreenUiState> = _splashScreenState

    fun checkAppState(){
        launch {
            if (dataStoreRepository.getFirstRun()){
                _splashScreenState.value = SplashScreenUiState.RunForAFirstTime
            } else {
                _splashScreenState.value = SplashScreenUiState.ContinueToApp
            }

        }
    }
}