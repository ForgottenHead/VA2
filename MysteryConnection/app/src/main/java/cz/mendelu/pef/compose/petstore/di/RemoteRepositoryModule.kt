package cz.mendelu.pef.compose.petstore.di

import cz.mendelu.pef.compose.petstore.communication.API
import cz.mendelu.pef.compose.petstore.communication.RemoteRepositoryImpl
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single { provideRemoteRepository(get()) }
}

fun provideRemoteRepository(petsAPI: API): RemoteRepositoryImpl
        = RemoteRepositoryImpl(petsAPI)

