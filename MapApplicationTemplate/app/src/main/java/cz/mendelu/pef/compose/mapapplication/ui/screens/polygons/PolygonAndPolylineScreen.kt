package cz.mendelu.pef.compose.mapapplication.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import cz.mendelu.pef.compose.mapapplication.database.PolygonAndPolyline
import cz.mendelu.pef.compose.mapapplication.elements.BackArrowScreen
import cz.mendelu.pef.compose.mapapplication.navigation.INavigationRouter
import cz.mendelu.pef.compose.mapapplication.ui.screens.polygons.PolygonAndPolylineUiState
import cz.mendelu.pef.compose.mapapplication.ui.screens.polygons.PolygonAndPolylineViewModel
import cz.mendelu.pef.compose.mapapplication.ui.screens.polygons.PolygonAndPolylinesScreenActions
import org.koin.androidx.compose.getViewModel

@Composable
fun PolygonAndPolylineScreen(navigation: INavigationRouter,
                        viewModel: PolygonAndPolylineViewModel = getViewModel()
) {

    val objects = remember { mutableStateListOf<PolygonAndPolyline>() }

    viewModel.polygonAndPolylinesUIState.value.let {
        when(it){
            is PolygonAndPolylineUiState.Default -> {

            }
            is PolygonAndPolylineUiState.PolygonsAndPolylines -> {
                objects.clear()
                objects.addAll(it.polygonsAndPolylines)
            }
        }
    }

    BackArrowScreen(
        topBarText = "Polygons and polylines",
        disablePadding = true,
        drawFullScreenContent = true,
        content = {
            PolygonAndPolylineScreenContent(
                actions = viewModel,
                objects = objects)
        },
        onBackClick = { navigation.returnBack() }
    )

}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun PolygonAndPolylineScreenContent(
    actions: PolygonAndPolylinesScreenActions,
    objects: List<PolygonAndPolyline>
) {

    val mapUiSettings by remember { mutableStateOf(
        MapUiSettings(
            zoomControlsEnabled = false,
            mapToolbarEnabled = false)
    ) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(49.62352578743681, 15.346186434122867), 5f)
    }


    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxHeight(),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ) {
            MapEffect { map->

            }
        }

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            OutlinedButton(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                onClick = {
                    actions.generateNewObjects()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Create polylines")
            }

            OutlinedButton(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                onClick = {
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Other function")
            }
        }
    }
}

