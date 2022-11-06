package cz.mendelu.pef.compose.mapapplication.navigation

sealed class Destination(
    val route: String
){
    object MainScreen : Destination(route = "main_screen")
    object MarkerClusteringScreen : Destination(route = "marker_cluster")
    object PolygonAndPolylineScreen : Destination(route = "polygon_and_polyline")
}
