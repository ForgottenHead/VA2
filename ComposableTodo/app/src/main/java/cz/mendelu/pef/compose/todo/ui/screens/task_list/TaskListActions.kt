package cz.mendelu.pef.compose.todo.ui.screens.task_list

interface TaskListActions {
    fun changeTaskState(id:Long, state: Boolean)
}