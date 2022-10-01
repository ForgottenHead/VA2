package cz.mendelu.pef.compose.todo.ui.screens.task_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.compose.todo.R
import cz.mendelu.pef.compose.todo.database.Task
import cz.mendelu.pef.compose.todo.navigation.INavigationRouter
import cz.mendelu.pef.compose.todo.ui.elements.PlaceHolderScreen
import cz.mendelu.pef.compose.todo.ui.theme.SecondaryColor
import cz.mendelu.pef.compose.todo.ui.theme.basicMargin
import cz.mendelu.pef.compose.todo.ui.theme.basicText
import cz.mendelu.pef.compose.todo.ui.theme.getBasicTextColor
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskListScreen(navigation: INavigationRouter,
                viewModel: TaskListScreenViewModel = getViewModel()
) {

    val tasks = remember { mutableStateListOf<Task>() }
    var showPlaceholder: Boolean by rememberSaveable {
        mutableStateOf(true)
    }

    viewModel.taskListUIState.value.let {
        when(it) {
            is TaskListUiState.Default -> {
                //TODO
                viewModel.loadTasks()
            }
            is TaskListUiState.Success -> {
                //TODO
                tasks.clear()
                tasks.addAll(it.tasks)
                showPlaceholder = tasks.isEmpty()
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title =
                { Text(text = "HALLO")},
            elevation = 8.dp) },
        floatingActionButton = { FloatingActionButton(onClick = {
            navigation.navigateToAddEditTask(-1L)
        }) {
            Text(text = "+")
        }}
    ) {
        TaskListScreenContent(
            tasks = tasks,
            showPlaceholder = showPlaceholder,
            navigation = navigation,
            actions = viewModel
        )
    }

}

@Composable
fun TaskListScreenContent(tasks: List<Task>,
                          showPlaceholder: Boolean,
                          navigation: INavigationRouter,
                          actions: TaskListActions){

    if (showPlaceholder){
        PlaceHolderScreen(image = R.drawable.placeholder_no_tasks, text = "No tasks")
        
    }else{
        LazyColumn{
            tasks.forEach {
                item(key = it.id) {
                    TaskListRow(task = it, onRowClick = { navigation.navigateToAddEditTask(it.id) }, actions = actions)
                }
            }
        }  
    }
    

}


@Composable
fun TaskListRow(task: Task, onRowClick: () -> Unit, actions: TaskListActions) {
    Surface(shape = RoundedCornerShape(5.dp), color = Color.Blue) {
        Row (modifier = Modifier.fillMaxWidth()){
            Text(text = task.text, color = Color.White)
        }
    }

}
