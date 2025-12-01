package basaraba.andrii.messageapp.database.impl.source

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import basaraba.andrii.messageapp.database.impl.room.MessageDao
import basaraba.andrii.messageapp.database.impl.room.toDomain
import basaraba.andrii.messageapp.database.impl.room.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MessagesLocalSourceImpl(
    private val dao: MessageDao
) : MessagesLocalSource {
    override fun getAllMessages(): Flow<List<MessageDomain>> =
        dao.getAllMessages().map { messages -> messages.map { it.toDomain() } }

    override suspend fun insertMessages(messages: List<MessageDomain>) {
        dao.insertMessages(messages = messages.map { it.toEntity() })
    }

    override suspend fun insertMessage(message: MessageDomain) {
        dao.insertMessage(message = message.toEntity())
    }

    override suspend fun getMessagesCount(): Int =
        dao.getMessagesCount()
}
