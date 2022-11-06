package cz.mendelu.pef.compose.mapapplication.di

import cz.mendelu.pef.compose.mapapplication.database.*
import org.koin.dsl.module

val repositoryModule = module {
    fun provideLocalPlacesRepository(dao: PlacesDao): IPlacesLocalRepository {
        return PlacesLocalRepositoryImpl(dao)
    }
    single { provideLocalPlacesRepository(get()) }
}