package basaraba.andrii.messageapp.data.impl.di

import android.content.res.AssetManager
import basaraba.andrii.messageapp.data.contract.repository.MessagesRepository
import basaraba.andrii.messageapp.data.impl.mock.MessagesMockSource
import basaraba.andrii.messageapp.data.impl.mock.MessagesMockSourceImpl
import basaraba.andrii.messageapp.data.impl.repository.MessagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val messageDataModule
    get() = module {
        single<AssetManager> { androidContext().assets }

        factoryOf(::MessagesMockSourceImpl) bind MessagesMockSource::class
        factoryOf(::MessagesRepositoryImpl) bind MessagesRepository::class
    }
