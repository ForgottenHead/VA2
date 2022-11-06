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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.util.MapUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.GridBasedAlgorithm
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.*
import cz.mendelu.pef.compose.mapapplication.database.Place
import cz.mendelu.pef.compose.mapapplication.elements.BackArrowScreen
import cz.mendelu.pef.compose.mapapplication.map.CustomMapRenderer
import cz.mendelu.pef.compose.mapapplication.map.MarkerUtil
import cz.mendelu.pef.compose.mapapplication.navigation.INavigationRouter
import cz.mendelu.pef.compose.mapapplication.ui.screens.markers.MarkerClusterUiState
import cz.mendelu.pef.compose.mapapplication.ui.screens.markers.MarkerClusterViewModel
import cz.mendelu.pef.compose.mapapplication.ui.screens.markers.MarkerClusteringScreenActions
import org.koin.androidx.compose.getViewModel

@Composable
fun MarkerClusterScreen(navigation: INavigationRouter,
               viewModel: MarkerClusterViewModel = getViewModel()
) {

    val places = remember { mutableStateListOf<Place>() }

    viewModel.placesUIState.value.let {
        when(it){
            is MarkerClusterUiState.Default -> {

            }
            is MarkerClusterUiState.Places -> {
                places.clear()
                places.addAll(it.places)
            }
        }
    }

    BackArrowScreen(
        topBarText = "Marker Clustering",
        disablePadding = true,
        drawFullScreenContent = true,
        content = {
            MarkerClusterScreenContent(
                actions = viewModel,
                places = places)
        },
        onBackClick = { navigation.returnBack() }
    )
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MarkerClusterScreenContent(
    actions: MarkerClusteringScreenActions,
    places: List<Place>
    ){

    val mapUiSettings by remember { mutableStateOf(
        MapUiSettings(
        zoomControlsEnabled = false,
        mapToolbarEnabled = false)
    ) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(49.62352578743681, 15.346186434122867), 5f)
    }

    var clusterManager by remember { mutableStateOf<ClusterManager<Place>?>(null)}
    val context = LocalContext.current
    var clusterRenderer by remember { mutableStateOf<CustomMapRenderer?>(null)}
    var currentMarker by remember { mutableStateOf<Marker?>(null)}

    if(places.isNotEmpty()){
        clusterManager?.addItems(places)
        clusterManager?.cluster()
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(modifier = Modifier.fillMaxHeight(),
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ){

            MapEffect(places) { map ->
                if ( clusterManager == null){
                    clusterManager = ClusterManager<Place>(context, map)
                }
                if (clusterRenderer == null){
                    clusterRenderer = CustomMapRenderer(context, map, clusterManager!!)
                }

                clusterManager?.apply {
                    renderer = clusterRenderer
                    algorithm = GridBasedAlgorithm()  //NonHierarchicalViewBasedAlgorithm()

                    renderer.setOnClusterItemClickListener(
                        object : ClusterManager.OnClusterItemClickListener<Place>{
                            override fun onClusterItemClick(item: Place?): Boolean {
                                if (currentMarker != null){
                                    currentMarker!!.setIcon(BitmapDescriptorFactory
                                        .fromBitmap(
                                            MarkerUtil.createCustomMarkerFromLayout(
                                                context, item!!, false)))
                                    currentMarker = null
                                }

                                currentMarker = clusterRenderer?.getMarker(item)
                                currentMarker!!.setIcon(BitmapDescriptorFactory
                                    .fromBitmap(
                                        MarkerUtil.createCustomMarkerFromLayout(
                                            context, item!!, true)))
                                return true
                            }
                        })

                }

                map.setOnCameraIdleListener { clusterManager?.cluster() }



            }
        }

        OutlinedButton(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .align(Alignment.BottomCenter),
            onClick = {
                actions.generateNewMarkers()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = "Create markers")
        }

    }
}
