package basaraba.andrii.domain.model

data class MessageDomain(
    val messageId: String,
    val message: String,
    val senderId: Long,
    val timestamp: Long,
    val isRead: Boolean = false
)
