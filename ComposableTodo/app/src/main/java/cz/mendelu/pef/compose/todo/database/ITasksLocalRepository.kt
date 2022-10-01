package cz.mendelu.pef.compose.todo.database

import kotlinx.coroutines.flow.Flow

interface ITasksLocalRepository {
    fun getAll(): Flow<List<Task>>
    suspend fun findById(id : Long): Task
    suspend fun insert(task: Task): Long
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
    suspend fun changeTaskState(id: Long, taskState: Boolean)
}

