package cz.mendelu.pef.compose.petstore.ui.screens

import cz.mendelu.pef.compose.petstore.architecture.BaseViewModel
import cz.mendelu.pef.compose.petstore.communication.RemoteRepositoryImpl

class MainScreenViewModel(private val petsRepository: RemoteRepositoryImpl) : BaseViewModel() {

}