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

}