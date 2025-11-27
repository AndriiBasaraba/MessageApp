package basaraba.andrii.messageapp.domain.contract.use_case

import basaraba.andrii.domain.model.MessageDomain
import kotlinx.coroutines.flow.Flow

interface GetAllMessagesUseCase {
    operator fun invoke(): Flow<List<MessageDomain>>
}
