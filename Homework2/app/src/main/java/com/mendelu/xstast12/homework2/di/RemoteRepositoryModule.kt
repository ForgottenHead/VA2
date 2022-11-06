package com.mendelu.xstast12.homework2.di

import com.mendelu.xstast12.homework2.communication.IMockRemoteRepository
import com.mendelu.xstast12.homework2.communication.MockAPI
import com.mendelu.xstast12.homework2.communication.MockRemoteRepositoryImpl
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single { provideRemoteRepository(get()) }
}

fun provideRemoteRepository(mockAPI: MockAPI): MockRemoteRepositoryImpl
        = MockRemoteRepositoryImpl(mockAPI)

