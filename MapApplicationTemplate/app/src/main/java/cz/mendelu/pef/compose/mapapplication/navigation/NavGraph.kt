package cz.mendelu.pef.compose.mapapplication.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.mendelu.pef.compose.mapapplication.ui.theme.screens.MainScreen
import cz.mendelu.pef.compose.mapapplication.ui.theme.screens.MarkerClusterScreen
import cz.mendelu.pef.compose.mapapplication.ui.theme.screens.PolygonAndPolylineScreen

@ExperimentalFoundationApi
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember { NavigationRouterImpl(navController) },
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination){

        composable(Destination.MainScreen.route) {
            MainScreen(navigation = navigation)
        }

        composable(Destination.MarkerClusteringScreen.route) {
            MarkerClusterScreen(navigation = navigation)
        }

        composable(Destination.PolygonAndPolylineScreen.route) {
            PolygonAndPolylineScreen(navigation = navigation)
        }
    }
}
