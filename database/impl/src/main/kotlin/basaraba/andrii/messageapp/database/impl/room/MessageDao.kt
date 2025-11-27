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

    @Query("SELECT * FROM message")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("DELETE FROM message")
    suspend fun deleteAllMessages()
}
