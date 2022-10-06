package cz.mendelu.pef.compose.petstore.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun getNavController(): NavController
    fun returnBack()
    fun navigateToMainScreen()
}