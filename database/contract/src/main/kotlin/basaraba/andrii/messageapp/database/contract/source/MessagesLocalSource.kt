package basaraba.andrii.messageapp.database.contract.source

import basaraba.andrii.domain.model.MessageDomain
import kotlinx.coroutines.flow.Flow

interface MessagesLocalSource {
    fun getAllMessages(): Flow<List<MessageDomain>>

    suspend fun insertMessage(message: MessageDomain)
}
