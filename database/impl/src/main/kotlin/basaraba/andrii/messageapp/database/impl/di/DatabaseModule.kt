package basaraba.andrii.messageapp.database.impl.di

import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import basaraba.andrii.messageapp.database.impl.source.MessagesLocalSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule
    get() = module {
        //database
        single { provideAppDatabase(androidApplication().applicationContext) }
        single { provideMessageDao(get()) }

        //local source
        factoryOf(::MessagesLocalSourceImpl) bind MessagesLocalSource::class
    }
