package cz.mendelu.pef.compose.todo.di

import cz.mendelu.pef.compose.todo.ToDoApplication
import cz.mendelu.pef.compose.todo.database.TasksDatabase
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(): TasksDatabase = TasksDatabase.getDatabase(ToDoApplication.appContext)
    single {
        provideDatabase()
    }
}