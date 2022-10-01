package cz.mendelu.pef.compose.petstore.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.mendelu.pef.compose.petstore.R
import cz.mendelu.pef.compose.petstore.constants.Constants
import cz.mendelu.pef.compose.petstore.extensions.getValue
import cz.mendelu.pef.compose.petstore.extensions.removeValue
import cz.mendelu.pef.compose.petstore.models.Pet
import cz.mendelu.pef.compose.petstore.models.ScreenState
import cz.mendelu.pef.compose.petstore.navigation.INavigationRouter
import cz.mendelu.pef.compose.petstore.ui.elements.ErrorScreen
import cz.mendelu.pef.compose.petstore.ui.elements.LoadingScreen
import cz.mendelu.pef.compose.petstore.ui.theme.Pink80
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfPetsScreen(navigation: INavigationRouter,
                viewModel: ListOfPetsViewModelViewModel = getViewModel()
) {

    val screenState: MutableState<ScreenState<List<Pet>>> = rememberSaveable{
        mutableStateOf(ScreenState.Loading())
    }

    viewModel.petsUiState.value.let {
        when(it){
            is ListOfPetsUiState.Start -> {
                LaunchedEffect(it){
                    viewModel.loadPets()
                }
            }


            is ListOfPetsUiState.Error -> {
                screenState.value = ScreenState.Error(it.error)

            }

            is ListOfPetsUiState.Success -> screenState.value = ScreenState.DataLoaded(it.data)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .weight(1.5f)
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colorScheme.background
            )
        },
        content = {
            PetsListScreenContent(
                screenState = screenState.value,
                paddingValues = it,
                navigation = navigation,
                viewModel = viewModel
            )
        },
    )
}

@Composable
fun PetsListScreenContent(
    viewModel: ListOfPetsViewModelViewModel,
    screenState: ScreenState<List<Pet>>,
    paddingValues: PaddingValues,
    navigation: INavigationRouter){

    screenState.let {
        when(it){
            is ScreenState.DataLoaded -> PetsList(paddingValues = paddingValues,
                navigation = navigation,
                pets = it.data,
                viewModel = viewModel)
            is ScreenState.Error -> ErrorScreen(text = stringResource(id = it.error))
            is ScreenState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun PetsList(paddingValues: PaddingValues,
             navigation: INavigationRouter,
             pets: List<Pet>,
             viewModel: ListOfPetsViewModelViewModel){

    val refreshing = rememberSaveable { mutableStateOf(false) }

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing.value), onRefresh = {viewModel.loadPets()}) {

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            pets.forEach {
                item(key = it.id) {
                    PetRow(
                        pet = it,
                        onRowClick = {
                            navigation.navigateToPetDetail(it.id)
                        }
                    )
                }
            }
        }

    }

}

@Composable
fun PetRow(pet: Pet,
           onRowClick: () -> Unit){


    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
            .padding(bottom = 10.dp)
            .clickable(onClick = onRowClick)
    ) {

            RowComponent(pet = pet)
        


    }
}

@Composable
fun RowComponent(pet:Pet) {
    Surface(
        shape = RoundedCornerShape(40),
        color = Pink80,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(60.dp)
                ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = pet.name?:"")
        }
    }
}