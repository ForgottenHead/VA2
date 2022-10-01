package cz.mendelu.pef.compose.todo.ui.screens.task_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.compose.todo.architecture.BaseViewModel
import cz.mendelu.pef.compose.todo.database.ITasksLocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskListScreenViewModel(private val repository: ITasksLocalRepository) : BaseViewModel(), TaskListActions{

    val taskListUIState: MutableState<TaskListUiState> = mutableStateOf(TaskListUiState.Default())

    fun loadTasks(){
        launch {
            repository.getAll().collect {
                taskListUIState.value = TaskListUiState.Success(it)
            }
        }
    }

    override fun changeTaskState(id: Long, state: Boolean) {
        launch {
            repository.changeTaskState(id, state)
        }
    }
}