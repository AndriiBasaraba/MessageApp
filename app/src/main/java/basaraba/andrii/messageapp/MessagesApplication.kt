package basaraba.andrii.messageapp

import android.app.Application
import basaraba.andrii.messageapp.database.impl.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

internal class MessagesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)

            modules(
                arrayListOf(
                    databaseModule
                )
            )
        }
    }
}
