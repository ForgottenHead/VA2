package cz.mendelu.pef.compose.todo.ui.screens.task_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
import cz.mendelu.pef.compose.todo.R
import cz.mendelu.pef.compose.todo.navigation.INavigationRouter
import cz.mendelu.pef.compose.todo.ui.elements.BackArrowScreen
import cz.mendelu.pef.compose.todo.ui.screens.map.MapActions
import cz.mendelu.pef.compose.todo.ui.screens.map.MapScreenViewModel
import cz.mendelu.pef.compose.todo.ui.theme.*
import org.koin.androidx.compose.getViewModel

@Composable
fun MapScreen(navigation: INavigationRouter,
              viewModel: MapScreenViewModel = getViewModel(),
              latitude: Double?,
              longitude: Double?
) {

    BackArrowScreen(
        topBarText = stringResource(R.string.map),
        disablePadding = true,
        drawFullScreenContent = true,
        content = {
            MapScreenContent(
                latitude = if (latitude != null) latitude else 49.20963543403347,
                longitude = if (longitude != null) longitude else 16.614246410843442,
                actions = viewModel,
                onButtonClick = {
                    if (viewModel.locationChanged) {
                        navigation.returnFromMap(viewModel.latitude!!, viewModel.longitude!!)
                    } else {
                        navigation.returnBack()
                    }
                })
        },
        onBackClick = { navigation.returnBack() }
    )
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapScreenContent(
    latitude: Double,
    longitude: Double,
    actions: MapActions,
    onButtonClick: () -> Unit,){

    var latitudeState by rememberSaveable { mutableStateOf(latitude) }
    var longitudeState by rememberSaveable { mutableStateOf(longitude) }

    val mapUiSettings by remember { mutableStateOf(MapUiSettings(
        zoomControlsEnabled = false,
        mapToolbarEnabled = false)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitudeState, longitudeState), 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(modifier = Modifier.fillMaxHeight(),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ){

            MapEffect { map ->
                map.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener{
                    override fun onMarkerDrag(p0: Marker) {}
                    override fun onMarkerDragEnd(p0: Marker) {
                        latitudeState = p0.position.latitude
                        longitudeState = p0.position.longitude
                        actions.locationChanged(p0.position.latitude, p0.position.longitude)
                    }
                    override fun onMarkerDragStart(p0: Marker) {}
                })
            }

            Marker(
                state = MarkerState(position = LatLng(latitudeState,longitudeState)),
                draggable = true
            )
        }

        Box(modifier = Modifier
            .padding(halfMargin())
            .align(Alignment.TopCenter)) {
            MarkerHelp()
        }

        OutlinedButton(
            modifier = Modifier
                .padding(
                    start = basicMargin(),
                    end = basicMargin(),
                    bottom = basicMargin()
                ).align(Alignment.BottomCenter),
            onClick = onButtonClick,
            shape = RoundedCornerShape(roundedCorner()),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text(text = stringResource(R.string.save_location))
        }

    }
}

@Composable
fun MarkerHelp(){
    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(start = basicMargin(), end = basicMargin(), top = halfMargin(), bottom = halfMargin())
        ) {
            Text(
                style = basicText(),
                color = getBasicTextColor(),
                text = stringResource(R.string.marker_help)
                            )
        }
    }
}