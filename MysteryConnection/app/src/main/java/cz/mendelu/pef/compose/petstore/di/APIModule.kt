package cz.mendelu.pef.compose.petstore.di

import cz.mendelu.pef.compose.petstore.communication.API
import org.koin.dsl.module
import retrofit2.Retrofit

val  apiModule = module {
    single { provideApi(get()) }
}

fun provideApi(retrofit: Retrofit): API
        = retrofit.create(API::class.java)

