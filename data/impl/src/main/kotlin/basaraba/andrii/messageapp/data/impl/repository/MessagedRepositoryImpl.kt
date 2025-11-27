package basaraba.andrii.messageapp.data.impl.repository

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import kotlinx.coroutines.flow.Flow

internal class MessagedRepositoryImpl(
    //future implementations may include remote data sources (e.g., network APIs)
    private val localSource: MessagesLocalSource
) : MessagedRepository {
    override fun getAllMessages(): Flow<List<MessageDomain>> =
        localSource.getAllMessages()

    override suspend fun insertMessage(message: MessageDomain) {
        localSource.insertMessage(message)
    }
}
