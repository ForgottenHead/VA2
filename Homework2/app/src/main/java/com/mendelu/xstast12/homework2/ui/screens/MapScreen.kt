package com.mendelu.xstast12.homework2.ui.screens

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.mendelu.xstast12.homework2.model.Brno
import com.mendelu.xstast12.homework2.model.ScreenState
import com.mendelu.xstast12.homework2.navigation.INavigationRouter
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
                Log.e("error", it.toString())
            }
            is MapScreenUiState.Success -> {
                Log.e("OK", it.toString())
            }
        }
    }

}