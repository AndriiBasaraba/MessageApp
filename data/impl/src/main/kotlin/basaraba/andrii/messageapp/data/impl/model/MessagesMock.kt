package basaraba.andrii.messageapp.data.impl.model

import basaraba.andrii.domain.model.MessageDomain
import kotlinx.serialization.Serializable

@Serializable
internal data class MessagesMock(
    val messageId: String,
    val message: String,
    val senderId: Long,
    val timestamp: Long,
    val isRead: Boolean
)

internal fun MessagesMock.toDomain(): MessageDomain =
    MessageDomain(
        messageId = messageId,
        message = message,
        timestamp = timestamp,
        senderId = senderId,
        isRead = isRead
    )
