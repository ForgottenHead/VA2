package com.mendelu.xstast12.homework2.navigation

sealed class Destination(
    val route: String
) {
    object MapScreen : Destination(route = "map_screen")
}
