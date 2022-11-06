package com.mendelu.xstast12.homework2.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController)
    : INavigationRouter{
    override fun getNavController(): NavController {
        return navController
    }

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToMap() {
        navController.navigate(Destination.MapScreen.route)
    }
}