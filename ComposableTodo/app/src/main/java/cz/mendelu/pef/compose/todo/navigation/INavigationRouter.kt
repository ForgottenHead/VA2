package cz.mendelu.pef.compose.todo.navigation

import androidx.navigation.NavController

/**
 * Rozhraní pro navigaci. Ať to má nějakou štábní kulturu.
 */
interface INavigationRouter {
    // občas se hodí možnost získat NavController. Proto tu tato metoda je.
    fun getNavController(): NavController
    // obecný návrat zpět.
    fun returnBack()
    fun navigateToAddEditTask(id: Long?)
    fun navigateToMap(latitude: Double?, longitude: Double?)
    fun returnFromMap(latitude: Double, longitude: Double)
}