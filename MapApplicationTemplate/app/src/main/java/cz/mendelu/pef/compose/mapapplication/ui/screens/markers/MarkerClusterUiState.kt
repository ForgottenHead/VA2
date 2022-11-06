package cz.mendelu.pef.compose.mapapplication.ui.screens.markers

import cz.mendelu.pef.compose.mapapplication.database.Place

sealed class MarkerClusterUiState {
    class Default : MarkerClusterUiState()
    class Places(val places: List<Place>) : MarkerClusterUiState()
}