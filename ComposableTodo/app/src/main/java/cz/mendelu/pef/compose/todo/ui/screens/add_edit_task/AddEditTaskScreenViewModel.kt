package cz.mendelu.pef.compose.todo.ui.screens.add_edit_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import cz.mendelu.pef.compose.todo.architecture.BaseViewModel
import cz.mendelu.pef.compose.todo.constants.Constants
import cz.mendelu.pef.compose.todo.database.ITasksLocalRepository
import cz.mendelu.pef.compose.todo.database.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddEditTaskScreenViewModel(private val savedStateHandle: SavedStateHandle,
                                 private val repository: ITasksLocalRepository)
    : BaseViewModel(), AddEditTaskActions{

    val addEditTaskUIState: MutableState<AddEditTaskUiState> = mutableStateOf(AddEditTaskUiState.Default)

    var taskId: Long? = null

    var task: Task = Task("")
        get() {
            if (!savedStateHandle.contains(Constants.TASK)){
                savedStateHandle.set(Constants.TASK, Task(""))
            }
            return savedStateHandle.get(Constants.TASK)!!
        }
        set(value) {
            field = value
            savedStateHandle.set(Constants.TASK, field)
        }

    override fun saveTask() {
        if (task.text.isEmpty()){
            addEditTaskUIState.value = AddEditTaskUiState.TaskNameError
        } else {
            launch {
                if (taskId == null) {
                    repository.insert(task)
                } else {
                    repository.update(task)
                }
                addEditTaskUIState.value = AddEditTaskUiState.TaskSaved
            }
        }
    }

    override fun deleteTask() {
        launch {
            repository.delete(task)
            addEditTaskUIState.value = AddEditTaskUiState.TaskSaved
        }
    }

    override fun initTask() {
        if (taskId != null) {
            launch {
                task = repository.findById(taskId!!)
                addEditTaskUIState.value = AddEditTaskUiState.TaskLoaded
            }
        } else {
            addEditTaskUIState.value = AddEditTaskUiState.TaskLoaded
        }

    }


}