package cz.mendelu.pef.compose.todo.di

import cz.mendelu.pef.compose.todo.database.ITasksLocalRepository
import cz.mendelu.pef.compose.todo.database.TasksDao
import cz.mendelu.pef.compose.todo.database.TasksLocalRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideLocalTaskRepository(dao: TasksDao): ITasksLocalRepository {
        return TasksLocalRepositoryImpl(dao)
    }
    single { provideLocalTaskRepository(get()) }
}