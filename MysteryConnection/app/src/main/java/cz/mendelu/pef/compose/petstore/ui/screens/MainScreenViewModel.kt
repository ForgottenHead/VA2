package cz.mendelu.pef.compose.petstore.ui.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.compose.petstore.architecture.BaseViewModel
import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.communication.RemoteRepositoryImpl
import cz.mendelu.pef.compose.petstore.models.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel(private val dataRepository: RemoteRepositoryImpl) : BaseViewModel() {

    val mainScreenUIState: MutableState<MainScreenUiState<Data>>
        = mutableStateOf(MainScreenUiState.Start())

    fun loadData(){
        launch {
            val result = withContext(Dispatchers.IO){
                dataRepository.getItems()
            }


            when(result){
                is CommunicationResult.Error -> mainScreenUIState.value = MainScreenUiState.Error(1)
                is CommunicationResult.Exception -> mainScreenUIState.value = MainScreenUiState.Error(2)
                is CommunicationResult.Success -> {
                    mainScreenUIState.value = MainScreenUiState.Success(result.data)
                    Log.e("resultSucce:", result.data.data?.get(0)?.value.toString())
                }
            }
        }
    }
}