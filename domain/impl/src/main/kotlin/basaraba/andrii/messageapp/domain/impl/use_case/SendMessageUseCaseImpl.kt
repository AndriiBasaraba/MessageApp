package basaraba.andrii.messageapp.domain.impl.use_case

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagesRepository
import basaraba.andrii.messageapp.domain.contract.use_case.SendMessageUseCase
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class SendMessageUseCaseImpl(
    private val repository: MessagesRepository
) : SendMessageUseCase {
    @OptIn(ExperimentalTime::class)
    override suspend fun invoke(message: String, senderId: Long) {
        val newMessage = MessageDomain(
            messageId = UUID.randomUUID().toString(),
            message = message,
            senderId = senderId,
            timestamp = Clock.System.now().toEpochMilliseconds(),
        )
        repository.insertMessage(message = newMessage)
    }
}
