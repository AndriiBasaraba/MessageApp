package basaraba.andrii.messageapp.ui.chat.mapper

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.ui.chat.model.Message
import basaraba.andrii.messageapp.ui.chat.model.toUi
import basaraba.andrii.messageapp.ui.utils.formatDate
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds

internal interface MessageUiMapper {

    fun map(messages: List<MessageDomain>, activeUserId: Long): List<Message>
}


internal class MessageUiMapperImpl : MessageUiMapper {
    override fun map(messages: List<MessageDomain>, activeUserId: Long): List<Message> {
        if (messages.isEmpty()) return emptyList()

        val result = mutableListOf<Message>()

        messages.forEachIndexed { index, item ->
            val previous = messages.getOrNull(index - 1)
            val next = messages.getOrNull(index + 1)

            val isHeaderNeeded = previous == null ||
                    item.timestamp - previous.timestamp > 1.hours.inWholeMilliseconds

            if (isHeaderNeeded) {
                val (day, time) = item.timestamp.formatDate()
                result.add(Message.Header(day = day, time = time))
            }

            val isSmallSpacing = next != null &&
                    (next.senderId == item.senderId &&
                            next.timestamp - item.timestamp <= 20.seconds.inWholeMilliseconds)

            result.add(
                item.toUi(
                    isMyMessage = item.senderId == activeUserId,
                    isSmallSpacing = isSmallSpacing
                )
            )
        }

        return result
    }
}
