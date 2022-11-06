package com.mendelu.xstast12.homework2.di

import com.mendelu.xstast12.homework2.communication.MockAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val  apiModule = module {
    single { provideApi(get()) }
}

fun provideApi(retrofit: Retrofit): MockAPI
        = retrofit.create(MockAPI::class.java)