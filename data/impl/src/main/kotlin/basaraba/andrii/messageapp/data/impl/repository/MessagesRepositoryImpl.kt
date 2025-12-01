package basaraba.andrii.messageapp.data.impl.repository

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagesRepository
import basaraba.andrii.messageapp.data.impl.mock.MessagesMockSource
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

internal class MessagesRepositoryImpl(
    //future implementations may include remote data sources (e.g., network APIs)
    private val localSource: MessagesLocalSource,
    private val mockSource: MessagesMockSource
) : MessagesRepository {
    override fun getAllMessages(): Flow<List<MessageDomain>> =
        localSource.getAllMessages()
            .onStart { prefillWithMock() }

    private suspend fun prefillWithMock() = withContext(Dispatchers.IO) {
        if (localSource.getMessagesCount() == 0) {
            localSource.insertMessages(mockSource.getMockMessages())
        }
    }

    override suspend fun insertMessage(message: MessageDomain) {
        localSource.insertMessage(message)
    }
}
