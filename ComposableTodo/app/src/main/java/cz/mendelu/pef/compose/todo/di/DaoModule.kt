package cz.mendelu.pef.compose.todo.di

import cz.mendelu.pef.compose.todo.database.TasksDao
import cz.mendelu.pef.compose.todo.database.TasksDatabase
import org.koin.dsl.module

val daoModule = module {
    fun provideTasksDao(database: TasksDatabase): TasksDao = database.tasksDao()
    single {
        provideTasksDao(get())
    }
}