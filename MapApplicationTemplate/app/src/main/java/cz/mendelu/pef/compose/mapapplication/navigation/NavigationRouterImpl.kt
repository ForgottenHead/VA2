package cz.mendelu.pef.compose.mapapplication.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun getNavController(): NavController = navController

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToMarkerCluster() {
        navController.navigate(Destination.MarkerClusteringScreen.route)
    }

    override fun navigateToPolygonAndPolyline() {
        navController.navigate(Destination.PolygonAndPolylineScreen.route)
    }

}