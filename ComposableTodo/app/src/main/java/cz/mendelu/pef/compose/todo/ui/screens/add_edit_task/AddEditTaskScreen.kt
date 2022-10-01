package cz.mendelu.pef.compose.todo.ui.screens.add_edit_task

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import cz.mendelu.pef.compose.todo.database.Task
import cz.mendelu.pef.compose.todo.navigation.INavigationRouter
import cz.mendelu.pef.compose.todo.ui.elements.BackArrowScreen
import cz.mendelu.pef.compose.todo.ui.elements.TextInputField
import org.koin.androidx.compose.getViewModel

@Composable
fun AddEditTaskScreen(
    navigation: INavigationRouter,
    id: Long?,
    viewModel: AddEditTaskScreenViewModel = getViewModel()
) {

    var task: Task by rememberSaveable {
        mutableStateOf(viewModel.task)
    }
    var loadingScreen: Boolean by rememberSaveable { mutableStateOf(true) }
    var taskNameError: String by rememberSaveable {
        mutableStateOf("")
    }

    viewModel.addEditTaskUIState.value.let {
        when(it){

            AddEditTaskUiState.Default -> {
                viewModel.taskId = id
                viewModel.initTask()
            }

            AddEditTaskUiState.TaskLoaded -> {
                loadingScreen = false
            }

            AddEditTaskUiState.TaskNameError -> {
                taskNameError = "Cannot be empty!"
            }

            AddEditTaskUiState.TaskRemoved -> {
                LaunchedEffect(it){
                    navigation.returnBack()
                }
            }

            AddEditTaskUiState.TaskSaved -> {
                LaunchedEffect(it){
                    navigation.returnBack()
                }

            }

        }
    }


    BackArrowScreen(topBarText = "Add Task",
        onBackClick = { navigation.returnBack() },
        content = { AddEditTaskScreenContent(
            actions = viewModel,
            loadingScreen = loadingScreen,
            navigation = navigation,
            task = task,
            taskNameError =  taskNameError
        ) })

}

@Composable
fun AddEditTaskScreenContent(
    actions: AddEditTaskActions,
    loadingScreen: Boolean,
    navigation: INavigationRouter,
    task: Task,
    taskNameError: String
) {

    var taskText:String by rememberSaveable{ mutableStateOf("")}

    TextInputField(
        value = taskText,
        hint = "Task name",
        onValueChange = { taskText = it
                        task.text = it},
        errorMessage = taskNameError)

    OutlinedButton(
        onClick = { actions.saveTask() },
        content = { Text(text = "Save")})


}
