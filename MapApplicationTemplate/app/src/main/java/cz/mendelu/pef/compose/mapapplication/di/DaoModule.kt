package cz.mendelu.pef.compose.mapapplication.di

import cz.mendelu.pef.compose.mapapplication.database.PlacesDao
import cz.mendelu.pef.compose.mapapplication.database.PlacesDatabase
import org.koin.dsl.module

val daoModule = module {
    fun providePlacesDao(database: PlacesDatabase): PlacesDao = database.placesDao()

    single {
        providePlacesDao(get())
    }

}