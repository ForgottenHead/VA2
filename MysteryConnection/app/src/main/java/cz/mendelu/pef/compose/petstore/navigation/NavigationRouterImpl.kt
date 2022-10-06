package cz.mendelu.pef.compose.petstore.navigation

import androidx.navigation.NavController
import cz.mendelu.pef.compose.petstore.constants.Constants


class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun getNavController(): NavController = navController

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToMainScreen() {
        navController.navigate(Destination.MainScreen.route)
    }

}