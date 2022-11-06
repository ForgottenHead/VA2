package cz.mendelu.pef.compose.mapapplication.ui.screens.polygons

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.compose.mapapplication.architecture.BaseViewModel
import cz.mendelu.pef.compose.mapapplication.database.Location
import cz.mendelu.pef.compose.mapapplication.database.PolygonAndPolyline
import kotlinx.coroutines.launch
import kotlin.random.Random

class PolygonAndPolylineViewModel : BaseViewModel(), PolygonAndPolylinesScreenActions {

    private val numberOfObjects = 1

    val polygonAndPolylinesUIState: MutableState<PolygonAndPolylineUiState> =
        mutableStateOf(PolygonAndPolylineUiState.Default())

    override fun generateNewObjects() {
        launch {
            var counter = 0L;
            val objects = mutableListOf<PolygonAndPolyline>()
            for (i in 0..numberOfObjects) {
                val polygonOrPolyline = (0..1).random()
                val numberOfPoints = (3..30).random()
                val locations: MutableList<Location> = mutableListOf()
                for (vertices in 0..numberOfPoints) {
                    val latitude = Random.nextDouble(48.0, 51.0)
                    val longitude = Random.nextDouble(12.0, 18.0)
                    locations.add(Location(latitude, longitude))
                }
                val pp = PolygonAndPolyline(locations)
                pp.type = polygonOrPolyline
                pp.id = counter
                counter++
                objects.add(pp)
            }
            polygonAndPolylinesUIState.value = PolygonAndPolylineUiState.PolygonsAndPolylines(objects)
        }
    }
}
