package com.mendelu.xstast12.homework2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mendelu.xstast12.homework2.architecture.BaseViewModel
import com.mendelu.xstast12.homework2.architecture.CommunicationResult
import com.mendelu.xstast12.homework2.communication.MockRemoteRepositoryImpl
import com.mendelu.xstast12.homework2.model.Brno
import com.mendelu.xstast12.homework2.model.Coordinate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapScreenViewModel(private val remoteRepository: MockRemoteRepositoryImpl): BaseViewModel() {

    val mapScreenUiState: MutableState<MapScreenUiState<Brno>>
    = mutableStateOf(MapScreenUiState.Start)


    fun loadData(){
        launch {
            val stores = withContext(Dispatchers.IO){
                remoteRepository.getStores()
            }

            when(stores){
                is CommunicationResult.Error -> mapScreenUiState.value = MapScreenUiState.Error(1)
                is CommunicationResult.Exception -> mapScreenUiState.value = MapScreenUiState.Error(2)
                is CommunicationResult.Success -> {
//                    val boundaries = withContext(Dispatchers.IO){
//                        remoteRepository.getBrnoBoundaries()
//                    }
//                    when(boundaries){
//                        is CommunicationResult.Error -> mapScreenUiState.value = MapScreenUiState.Error(3)
//                        is CommunicationResult.Exception -> mapScreenUiState.value = MapScreenUiState.Error(4)
//                        is CommunicationResult.Success -> {
//                            var brno = Brno(stores.data, boundaries.data)
//                            mapScreenUiState.value = MapScreenUiState.Success(brno)
//                        }
//                    }
                    mapScreenUiState.value = MapScreenUiState.Success(Brno(stores.data))

                }
            }






        }
    }
}