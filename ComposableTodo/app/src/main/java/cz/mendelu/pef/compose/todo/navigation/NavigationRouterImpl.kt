package cz.mendelu.pef.compose.todo.navigation

import androidx.navigation.NavController
import cz.mendelu.pef.compose.todo.constants.Constants

/**
 * Implelementace navigace.
 */
class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun getNavController(): NavController = navController

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToAddEditTask(id: Long?) {
        navController.navigate(Destination.AddEditTaskScreen.route + "/" + id)
    }

    override fun navigateToMap(latitude: Double?, longitude: Double?) {
        // Navigace je řešena přes Deeplinks, což znamená takovou url a tam něco musím poslat.
        // 500 a 500 lat a long nikdy nebudou
        if (latitude != null && longitude != null) {
            navController.navigate(Destination.MapScreen.route + "/" + latitude + "/" + longitude)
        } else {
            navController.navigate(Destination.MapScreen.route + "/500.0f/500.0f")
        }
    }

    override fun returnFromMap(latitude: Double, longitude: Double) {
        // speciální případ navigace, kdy se vracím zpět a musím si něco poslat.
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(Constants.LATITUDE, latitude)
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set(Constants.LONGITUDE, longitude)
        returnBack()
    }

}