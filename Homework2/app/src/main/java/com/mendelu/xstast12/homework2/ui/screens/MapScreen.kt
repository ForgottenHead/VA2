package com.mendelu.xstast12.homework2.ui.screens

import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.GridBasedAlgorithm
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.*
import com.mendelu.xstast12.homework2.map.CustomMapRenderer
import com.mendelu.xstast12.homework2.model.Brno
import com.mendelu.xstast12.homework2.model.ScreenState
import com.mendelu.xstast12.homework2.model.Store
import com.mendelu.xstast12.homework2.navigation.INavigationRouter
import com.mendelu.xstast12.homework2.ui.elements.ErrorScreen
import com.mendelu.xstast12.homework2.ui.elements.LoadingScreen
import com.mendelu.xstast12.homework2.ui.elements.MapCard
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navigation: INavigationRouter,
              viewModel: MapScreenViewModel = getViewModel()
) {

    val screenState: MutableState<ScreenState<Brno>> = rememberSaveable{
        mutableStateOf(ScreenState.Loading())
    }

    viewModel.mapScreenUiState.value.let {
        when(it){
            MapScreenUiState.Start -> {
                LaunchedEffect(it){
                    viewModel.loadData()

                }

            }
            is MapScreenUiState.Error -> {
                screenState.value = ScreenState.Error(it.error)

            }
            is MapScreenUiState.Success -> {
                screenState.value = ScreenState.DataLoaded(it.data)
            }
        }
    }
    
    MapScreenContentSwitcher(screenState = screenState.value)
    

}

@Composable
fun MapScreenContentSwitcher(screenState: ScreenState<Brno>) {
    screenState.let {
        when(it){
            is ScreenState.DataLoaded -> MapScreenContent(it.data)
            is ScreenState.Error -> ErrorScreen(text = it.error.toString())
            is ScreenState.Loading -> LoadingScreen()
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapScreenContent(brno: Brno) {
    val mapUiSettings by remember { mutableStateOf(
        MapUiSettings(
            zoomControlsEnabled = false,
            mapToolbarEnabled = false)
    ) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(49.201852, 16.613671),
            10f)
    }

    var clusterManager by remember { mutableStateOf<ClusterManager<Store>?>(null)}
    val context = LocalContext.current
    var clusterRenderer by remember { mutableStateOf<CustomMapRenderer?>(null)}
    var currentMarker by remember { mutableStateOf<Marker?>(null)}

    if (brno.stores!!.isNotEmpty()){
        clusterManager?.addItems(brno.stores)
        clusterManager?.cluster()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        GoogleMap(uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ){

            MapEffect(brno.stores){ map ->
                map.addPolygon(
                    PolygonOptions()
                    .addAll(brno.boundaries.allCoordinates!!).strokeWidth(5f))
                
                if ( clusterManager == null){
                    clusterManager = ClusterManager<Store>(context, map)
                }
                if (clusterRenderer == null){
                    clusterRenderer = CustomMapRenderer(context, map, clusterManager!!)
                }
                clusterManager?.setAnimation(true)

                clusterManager?.apply {
                    renderer = clusterRenderer
                   // algorithm = GridBasedAlgorithm()
                    algorithm = NonHierarchicalDistanceBasedAlgorithm()

                    renderer.setOnClusterItemClickListener { item ->
                        if (currentMarker != null){
                            currentMarker = null
                        }

                        map.animateCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(LatLng(item!!.latitude, item.longitude), 16f)
                        )

                        currentMarker = clusterRenderer?.getMarker(item)
                        true
                    }
                }
                
                map.setOnCameraIdleListener {
                    clusterManager?.cluster()
                }
            }

        }
        
        if (currentMarker != null){
            MapCard(marker = currentMarker!!)
        }

    }
}

