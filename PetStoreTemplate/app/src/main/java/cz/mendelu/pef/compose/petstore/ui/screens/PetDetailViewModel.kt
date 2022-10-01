package cz.mendelu.pef.compose.petstore.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.compose.petstore.R
import cz.mendelu.pef.compose.petstore.architecture.BaseViewModel
import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.communication.PetsRemoteRepositoryImpl
import cz.mendelu.pef.compose.petstore.models.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetDetailViewModel(private var petsRepository: PetsRemoteRepositoryImpl) : BaseViewModel() {
    val petDetailUiState: MutableState<PetDetailUiState<Pet>> =
        mutableStateOf(PetDetailUiState.Start())

    var petId: Long? = null
    // var pet: Pet? = null

    fun loadPet() {
        launch {
            val result = withContext(Dispatchers.IO) {
                petsRepository.pet(id = petId!!)
            }

            when (result) {
                is CommunicationResult.Success -> petDetailUiState.value =
                    PetDetailUiState.Success(result.data)
                is CommunicationResult.Error -> petDetailUiState.value =
                    PetDetailUiState.Error(R.string.failed)
                is CommunicationResult.Exception -> petDetailUiState.value =
                    PetDetailUiState.Error(R.string.no_internet_connection)

            }
        }
    }

    fun deletePet(){
        launch {
            val result = withContext(Dispatchers.IO) {
                petsRepository.deletePet(id = petId!!)
            }

            when (result) {
                is CommunicationResult.Success -> petDetailUiState.value =
                    PetDetailUiState.PetDeleted()
                is CommunicationResult.Error ->{
                    petDetailUiState.value =
                        PetDetailUiState.Error(R.string.not_removed)
                }
                is CommunicationResult.Exception -> petDetailUiState.value =
                    PetDetailUiState.Error(R.string.no_internet_connection)

            }
        }
    }


}