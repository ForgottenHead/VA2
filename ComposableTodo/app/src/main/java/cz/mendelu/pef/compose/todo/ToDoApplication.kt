package cz.mendelu.pef.compose.todo

import android.app.Application
import android.content.Context
import cz.mendelu.pef.compose.todo.di.*
import org.koin.android.BuildConfig

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Main application class.
 * Initializes Koin DI.
 * Todo extension na ziskani hodnoty z arguments
 * TODO AddEdit latitude a longitude v contentu na hodine blblo. Musel tam byt ten hack
 */
class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ToDoApplication)
            modules(listOf(
                databaseModule,
                daoModule,
                repositoryModule,
                viewModelModule,
                dataStoreModule
            ))
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }

}