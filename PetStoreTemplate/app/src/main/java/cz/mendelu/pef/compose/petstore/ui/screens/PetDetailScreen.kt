package cz.mendelu.pef.compose.petstore.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.compose.petstore.R
import cz.mendelu.pef.compose.petstore.models.Pet
import cz.mendelu.pef.compose.petstore.models.ScreenState
import cz.mendelu.pef.compose.petstore.navigation.INavigationRouter
import cz.mendelu.pef.compose.petstore.ui.elements.BackArrowScreen
import cz.mendelu.pef.compose.petstore.ui.elements.ErrorScreen
import cz.mendelu.pef.compose.petstore.ui.elements.LoadingScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun PetDetailScreen(navigation: INavigationRouter,
                    id: Long,
                viewModel: PetDetailViewModel = getViewModel()
) {
    viewModel.petId = id
    val screenState:MutableState<ScreenState<Pet>> = rememberSaveable{
        mutableStateOf(ScreenState.Loading())
    }

    viewModel.petDetailUiState.value.let {
        when(it){
            is PetDetailUiState.Error -> screenState.value = ScreenState.Error(it.error)
            is PetDetailUiState.PetDeleted -> {
                LaunchedEffect(it){
                    navigation.returnFromDetailWhenDeleted()
                }

            }
            is PetDetailUiState.Start -> {
                LaunchedEffect(it){
                    viewModel.loadPet()
                }
            }
            is PetDetailUiState.Success -> screenState.value = ScreenState.DataLoaded(it.data)
        }
    }


    BackArrowScreen(
        topBarText = stringResource(R.string.pet_detail),
        content = {
            PefDetailScreenContent(
                screenState = screenState.value,
                navigation = navigation
            )
        },
        actions = {
            IconButton(onClick = {
                viewModel.deletePet()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        onBackClick = { navigation.returnBack() }
    )

}

@Composable
fun PefDetailScreenContent(
    screenState: ScreenState<Pet>,
    navigation: INavigationRouter){

    screenState.let {
        when(it){
            is ScreenState.DataLoaded -> PetDetailScreenDataLoadedScreen(it.data)
            is ScreenState.Error -> ErrorScreen(text = stringResource(id = it.error))
            is ScreenState.Loading -> LoadingScreen()
        }
    }


}

@Composable
fun PetDetailScreenDataLoadedScreen(pet: Pet) {
    Text(text = pet.name)
    Text(text = pet.id.toString())
    Log.e("nieco", pet.id.toString())


}

