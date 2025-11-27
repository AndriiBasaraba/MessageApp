package basaraba.andrii.messageapp.domain.impl.di

import basaraba.andrii.messageapp.domain.contract.use_case.GetAllMessagesUseCase
import basaraba.andrii.messageapp.domain.contract.use_case.SendMessageUseCase
import basaraba.andrii.messageapp.domain.impl.use_case.GetAllMessagesUseCaseImpl
import basaraba.andrii.messageapp.domain.impl.use_case.SendMessageUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val messageDomainModule
    get() = module {
        factoryOf(::SendMessageUseCaseImpl) bind SendMessageUseCase::class
        factoryOf(::GetAllMessagesUseCaseImpl) bind GetAllMessagesUseCase::class
    }
