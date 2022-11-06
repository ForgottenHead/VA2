package cz.mendelu.pef.compose.mapapplication.ui.screens.markers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.compose.mapapplication.architecture.BaseViewModel
import cz.mendelu.pef.compose.mapapplication.database.IPlacesLocalRepository
import cz.mendelu.pef.compose.mapapplication.database.Place
import kotlinx.coroutines.launch
import kotlin.random.Random

class MarkerClusterViewModel(private val repository: IPlacesLocalRepository) : BaseViewModel(),
    MarkerClusteringScreenActions {

    private val numberOfMarkers = 100

    val placesUIState: MutableState<MarkerClusterUiState> = mutableStateOf(MarkerClusterUiState.Default())

    init {
        launch {
            repository.getAll().collect{
                placesUIState.value = MarkerClusterUiState.Places(it)
            }
        }
    }

    override fun generateNewMarkers() {
        launch {
            val places = mutableListOf<Place>()
            for (i in 0..numberOfMarkers) {
                val latitude = Random.nextDouble(48.0, 51.0)
                val longitude = Random.nextDouble(12.0, 18.0)
                val type = (0..1).random()
                val place = Place(latitude, longitude)
                place.type = type
                places.add(place)
            }
            repository.insert(places)
        }
    }

}