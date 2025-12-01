package basaraba.andrii.messageapp.data.contract.repository

import basaraba.andrii.domain.model.MessageDomain
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    fun getAllMessages(): Flow<List<MessageDomain>>

    suspend fun insertMessage(message: MessageDomain)
}
