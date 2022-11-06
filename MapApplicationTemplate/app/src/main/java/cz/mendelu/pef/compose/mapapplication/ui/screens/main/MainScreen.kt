package cz.mendelu.pef.compose.mapapplication.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.mendelu.pef.compose.mapapplication.navigation.INavigationRouter
import cz.mendelu.pef.compose.mapapplication.ui.screens.main.MainScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navigation: INavigationRouter,
               viewModel: MainScreenViewModel = getViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            OutlinedButton(
                onClick = {
                    navigation.navigateToMarkerCluster()
                }) {
                Text(text = "Marker clustering")
            }

            OutlinedButton(
                onClick = {
                    navigation.navigateToPolygonAndPolyline()
                }) {
                Text(text = "Polygons and polylines")
            }
        }


    }


}
