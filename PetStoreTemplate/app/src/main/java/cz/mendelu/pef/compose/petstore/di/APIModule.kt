package cz.mendelu.pef.compose.petstore.di

import cz.mendelu.pef.compose.petstore.communication.PetsAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val  apiModule = module {
    single { providePetsApi(get()) }
}

fun providePetsApi(retrofit: Retrofit): PetsAPI
        = retrofit.create(PetsAPI::class.java)

