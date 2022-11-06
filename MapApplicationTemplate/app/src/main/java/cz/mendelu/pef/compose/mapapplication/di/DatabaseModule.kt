package cz.mendelu.pef.compose.mapapplication.di

import cz.mendelu.pef.compose.mapapplication.MapApplication
import cz.mendelu.pef.compose.mapapplication.database.PlacesDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(): PlacesDatabase = PlacesDatabase.getDatabase(MapApplication.appContext)

    single {
        provideDatabase()
    }


}