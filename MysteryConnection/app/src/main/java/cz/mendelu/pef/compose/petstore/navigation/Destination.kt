package cz.mendelu.pef.compose.petstore.navigation

sealed class Destination(
    val route: String
){
    object MainScreen : Destination(route = "main")
}
