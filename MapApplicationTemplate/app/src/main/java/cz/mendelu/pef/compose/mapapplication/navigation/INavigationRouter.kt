package cz.mendelu.pef.compose.mapapplication.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun getNavController(): NavController
    fun returnBack()
    fun navigateToMarkerCluster()
    fun navigateToPolygonAndPolyline()
}