package basaraba.andrii.messageapp

import android.app.Application
import basaraba.andrii.messageapp.data.impl.di.messageDataModule
import basaraba.andrii.messageapp.database.impl.di.databaseModule
import basaraba.andrii.messageapp.domain.impl.di.messageDomainModule
import basaraba.andrii.messageapp.ui.di.messagesUiModule
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
                    databaseModule,
                    messageDataModule,
                    messageDomainModule,
                    messagesUiModule
                )
            )
        }
    }
}
