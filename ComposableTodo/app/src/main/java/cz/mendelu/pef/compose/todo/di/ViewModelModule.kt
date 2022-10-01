package cz.mendelu.pef.compose.todo.di

import androidx.lifecycle.SavedStateHandle
import cz.mendelu.pef.compose.todo.ui.activities.viewmodels.AppIntroViewModel
import cz.mendelu.pef.compose.todo.ui.activities.viewmodels.MainActivityViewModel
import cz.mendelu.pef.compose.todo.ui.activities.viewmodels.SplashScreenActivityViewModel
import cz.mendelu.pef.compose.todo.ui.screens.add_edit_task.AddEditTaskScreenViewModel
import cz.mendelu.pef.compose.todo.ui.screens.map.MapScreenViewModel
import cz.mendelu.pef.compose.todo.ui.screens.task_list.TaskListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // for screens
    viewModel { TaskListScreenViewModel(get()) }
    viewModel { AddEditTaskScreenViewModel(get(),get()) }
    viewModel { MapScreenViewModel(get()) }

    // for activities
    viewModel { SplashScreenActivityViewModel(get()) }
    viewModel { MainActivityViewModel() }
    viewModel { AppIntroViewModel(get()) }


    // For the saved state handle
    fun provideSavedStateHandle(): SavedStateHandle{
        return SavedStateHandle()
    }

    factory { provideSavedStateHandle() }

}
