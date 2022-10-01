package cz.mendelu.pef.compose.todo.di

import android.content.Context
import cz.mendelu.pef.compose.todo.datastore.DataStoreRepositoryImpl
import cz.mendelu.pef.compose.todo.datastore.IDataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { provideDataStoreRepository(androidContext()) }
}

fun provideDataStoreRepository(context: Context): IDataStoreRepository
        = DataStoreRepositoryImpl(context)