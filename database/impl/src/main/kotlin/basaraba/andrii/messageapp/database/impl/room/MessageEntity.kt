package basaraba.andrii.messageapp.database.impl.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import basaraba.andrii.domain.model.MessageDomain

@Entity(tableName = "message")
internal data class MessageEntity(
    @ColumnInfo(name = "message_id") val messageId: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "sender_id") val senderId: Long,
    @ColumnInfo(name = "is_read") val isRead: Boolean,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "db_id") val dbId: Long = 1
)

internal fun MessageEntity.toDomain(): MessageDomain =
    MessageDomain(
        messageId = messageId,
        message = message,
        timestamp = timestamp,
        senderId = senderId,
        isRead = isRead
    )

internal fun MessageDomain.toEntity(): MessageEntity =
    MessageEntity(
        messageId = messageId,
        message = message,
        timestamp = timestamp,
        senderId = senderId,
        isRead = isRead
    )
