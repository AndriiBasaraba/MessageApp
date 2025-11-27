package basaraba.andrii.messageapp.data.impl.di

import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.data.impl.repository.MessagedRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val messageDataModule
    get() = module {
        factoryOf(::MessagedRepositoryImpl) bind MessagedRepository::class
    }
