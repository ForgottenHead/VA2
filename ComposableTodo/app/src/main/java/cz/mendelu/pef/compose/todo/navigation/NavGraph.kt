package cz.mendelu.pef.compose.todo.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.mendelu.pef.compose.todo.constants.Constants
import cz.mendelu.pef.compose.todo.ui.screens.add_edit_task.AddEditTaskScreen
import cz.mendelu.pef.compose.todo.ui.screens.task_list.MapScreen
import cz.mendelu.pef.compose.todo.ui.screens.task_list.TaskListScreen

/**
 * První composable funkce spuštěná při startu MainActivity.
 */
@ExperimentalFoundationApi
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember { NavigationRouterImpl(navController) },
    startDestination: String
) {

    // Obalíme všechny obrazovky do NavHost funkce.
    NavHost(
        navController = navController,
        startDestination = startDestination){

        // Seznam úkolů
        composable(Destination.TaskListScreen.route) {
            TaskListScreen(navigation = navigation)
        }

        // Přidání úkolu. Posílám id úkolu, defaultní hodnota je -1 protože
        // nemůžu poslat null.-
        composable(Destination.AddEditTaskScreen.route + "/{id}",
            arguments = listOf(
                navArgument(Constants.ID) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            val id = it.arguments?.getLong(Constants.ID)
            AddEditTaskScreen(navigation = navigation,  id = if (id != -1L) id else null)
        }

        // Mapa. Posílám zem. šířku a délku.
        composable(Destination.MapScreen.route + "/{latitude}/{longitude}",
                arguments = listOf(
                navArgument(Constants.LATITUDE) {
                    type = NavType.FloatType
                    defaultValue = 500.0f
                }, navArgument(Constants.LONGITUDE) {
                        type = NavType.FloatType
                        defaultValue = 500.0f
                    }
                )
        ) {
            // oběřím si, co vlastně došlo.
            val latitude = it.arguments?.getFloat(Constants.LATITUDE)
            val longitude = it.arguments?.getFloat(Constants.LONGITUDE)
            if (latitude != 500.0f  && longitude != 500.0f) {
                MapScreen(navigation = navigation, latitude = latitude!!.toDouble(), longitude = longitude!!.toDouble())
            } else {
                // do Composable funkce už null poslat můžu.
                MapScreen(navigation = navigation, latitude = null, longitude = null)
            }
        }
    }
}
