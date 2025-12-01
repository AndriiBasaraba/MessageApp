package basaraba.andrii.messageapp.database.impl.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)

    @Query("SELECT * FROM message")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM message")
    suspend fun deleteAllMessages()

    @Query("SELECT COUNT(*) FROM message")
    suspend fun getMessagesCount(): Int
}
