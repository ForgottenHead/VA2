package cz.mendelu.pef.compose.todo.ui.screens.map

import androidx.lifecycle.SavedStateHandle
import cz.mendelu.pef.compose.todo.architecture.BaseViewModel
import cz.mendelu.pef.compose.todo.constants.Constants

class MapScreenViewModel(private val savedStateHandle: SavedStateHandle) : BaseViewModel(), MapActions{

    var latitude: Double? = null
        get() {
            if (savedStateHandle.contains(Constants.LATITUDE)) return savedStateHandle.get(Constants.LATITUDE)!! else return null
        }
        set(value) {
            field = value
            savedStateHandle[Constants.LATITUDE] = value
        }

    var longitude: Double? = null
        get() {
            if (savedStateHandle.contains(Constants.LONGITUDE)) return savedStateHandle.get(Constants.LONGITUDE)!! else return null
        }
        set(value) {
            field = value
            savedStateHandle[Constants.LONGITUDE] = value
        }

    var locationChanged: Boolean = false
        get() {
            if (savedStateHandle.contains(Constants.LOCATION_CHANGED)) return savedStateHandle.get(Constants.LOCATION_CHANGED)!! else return false
        }
        set(value) {
            field = value
            savedStateHandle[Constants.LOCATION_CHANGED] = value
        }

    override fun locationChanged(latitude: Double, longitude: Double) {
        locationChanged = true
        this.latitude = latitude
        this.longitude = longitude
    }
}