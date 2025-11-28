package basaraba.andrii.messageapp.ui.chat.model

import basaraba.andrii.domain.model.MessageDomain
import java.util.UUID

internal interface Message {

    val id: String

    data class Header(
        override val id: String = UUID.randomUUID().toString(),
        val day: String,
        val time: String
    ) : Message

    data class Bubble(
        override val id: String,
        val message: String,
        val senderId: Long,
        val timestamp: Long,
        val isRead: Boolean,
        val isMyMessage: Boolean,
        val isSmallSpacing: Boolean
    ) : Message
}

internal fun MessageDomain.toUi(isMyMessage: Boolean, isSmallSpacing: Boolean): Message.Bubble =
    Message.Bubble(
        id = messageId,
        message = message,
        timestamp = timestamp,
        senderId = senderId,
        isRead = isRead,
        isMyMessage = isMyMessage,
        isSmallSpacing = isSmallSpacing
    )
