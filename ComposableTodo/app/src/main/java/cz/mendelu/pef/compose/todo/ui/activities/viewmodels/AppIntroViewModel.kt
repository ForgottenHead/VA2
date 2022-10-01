package cz.mendelu.pef.compose.todo.ui.activities.viewmodels

import cz.mendelu.pef.compose.todo.architecture.BaseViewModel
import cz.mendelu.pef.compose.todo.datastore.IDataStoreRepository

class AppIntroViewModel(private val dataStoreRepository: IDataStoreRepository) : BaseViewModel() {
    suspend fun setFirstRun(){
        dataStoreRepository.setFirstRun()
    }
}