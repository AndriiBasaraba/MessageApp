package basaraba.andrii.messageapp.database.impl

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import basaraba.andrii.messageapp.database.impl.room.MessageDao
import basaraba.andrii.messageapp.database.impl.room.MessageEntity
import basaraba.andrii.messageapp.database.impl.room.MessagesDb
import basaraba.andrii.messageapp.database.impl.source.MessagesLocalSourceImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MessagesLocalSourceIntegrationTest {

    private lateinit var db: MessagesDb
    private lateinit var dao: MessageDao
    private lateinit var source: MessagesLocalSource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = MessagesDb.getInMemoryDb(context)
        dao = db.messageDao()
        source = MessagesLocalSourceImpl(dao)
    }

    @After
    fun teardown() {
        db.close()
    }


    @Test
    fun `insert and get all messages`() = runTest {
        val entity = MessageEntity(
            messageId = "1",
            message = "hello",
            senderId = 1,
            timestamp = 123,
            isRead = false
        )

        dao.insertMessage(entity)

        val messages = dao.getAllMessages().first()

        assertThat(messages).contains(entity)
        assertThat(messages).hasSize(1)
    }

    @Test
    fun `delete and clear database`() = runTest {
        val entity = MessageEntity(
            messageId = "1",
            message = "hello",
            senderId = 1,
            timestamp = 123,
            isRead = false
        )

        dao.insertMessage(entity)
        dao.deleteAllMessages()
        val messages = dao.getAllMessages().first()

        assertThat(messages).isEmpty()
    }
}
