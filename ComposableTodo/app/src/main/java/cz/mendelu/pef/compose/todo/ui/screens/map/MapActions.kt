package cz.mendelu.pef.compose.todo.ui.screens.map

interface MapActions {
    fun locationChanged(latitude: Double, longitude: Double)
}