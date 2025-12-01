package basaraba.andrii.messageapp.database.contract.source

import basaraba.andrii.domain.model.MessageDomain
import kotlinx.coroutines.flow.Flow

interface MessagesLocalSource {
    fun getAllMessages(): Flow<List<MessageDomain>>

    suspend fun insertMessage(message: MessageDomain)

    suspend fun insertMessages(messages: List<MessageDomain>)

    suspend fun getMessagesCount(): Int
}
