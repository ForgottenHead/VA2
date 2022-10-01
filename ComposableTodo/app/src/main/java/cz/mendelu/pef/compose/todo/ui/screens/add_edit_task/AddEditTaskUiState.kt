package cz.mendelu.pef.compose.todo.ui.screens.add_edit_task

sealed class AddEditTaskUiState {
    object Default : AddEditTaskUiState()
    object TaskSaved : AddEditTaskUiState()
    object TaskLoaded : AddEditTaskUiState()
    object TaskRemoved : AddEditTaskUiState()
    object TaskNameError : AddEditTaskUiState()
}