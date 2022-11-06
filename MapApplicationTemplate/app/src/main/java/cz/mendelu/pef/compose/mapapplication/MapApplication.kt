package cz.mendelu.pef.compose.mapapplication

import android.app.Application
import android.content.Context
import cz.mendelu.pef.compose.mapapplication.di.daoModule
import cz.mendelu.pef.compose.mapapplication.di.databaseModule
import cz.mendelu.pef.compose.mapapplication.di.repositoryModule
import cz.mendelu.pef.compose.mapapplication.di.viewModelModule
import org.koin.android.BuildConfig

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MapApplication)
            modules(listOf(
                databaseModule,
                daoModule,
                repositoryModule,
                viewModelModule,
            ))
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }

}