package cz.mendelu.pef.compose.todo.ui.screens.task_list

import cz.mendelu.pef.compose.todo.database.Task

sealed class TaskListUiState {
    class Default : TaskListUiState()
    class Success(val tasks: List<Task>) : TaskListUiState()
}