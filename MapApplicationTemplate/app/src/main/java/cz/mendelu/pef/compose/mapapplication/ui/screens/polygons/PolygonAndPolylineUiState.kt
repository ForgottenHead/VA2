package cz.mendelu.pef.compose.mapapplication.ui.screens.polygons

import cz.mendelu.pef.compose.mapapplication.database.PolygonAndPolyline

sealed class PolygonAndPolylineUiState {
    class Default : PolygonAndPolylineUiState()
    class PolygonsAndPolylines(val polygonsAndPolylines: List<PolygonAndPolyline>) : PolygonAndPolylineUiState()
}