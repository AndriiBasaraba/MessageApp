package basaraba.andrii.messageapp.domain.impl.use_case

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.domain.contract.use_case.GetAllMessagesUseCase
import kotlinx.coroutines.flow.Flow

internal class GetAllMessagesUseCaseImpl(
    private val repository: MessagedRepository
) : GetAllMessagesUseCase {
    override fun invoke(): Flow<List<MessageDomain>> = repository.getAllMessages()
}
