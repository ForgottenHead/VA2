package com.mendelu.xstast12.homework2

import android.app.Application
import android.content.Context
import com.mendelu.xstast12.homework2.di.apiModule
import com.mendelu.xstast12.homework2.di.remoteRepositoryModule
import com.mendelu.xstast12.homework2.di.retrofitModule
import com.mendelu.xstast12.homework2.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Homework2Application: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Homework2Application)
            modules(listOf(
                viewModelModule,
                apiModule,
                retrofitModule,
                remoteRepositoryModule
            ))
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}