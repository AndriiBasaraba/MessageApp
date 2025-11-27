package basaraba.andrii.database.impl.source

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import basaraba.andrii.messageapp.database.impl.room.MessageDao
import basaraba.andrii.messageapp.database.impl.room.MessageEntity
import basaraba.andrii.messageapp.database.impl.room.toDomain
import basaraba.andrii.messageapp.database.impl.room.toEntity
import basaraba.andrii.messageapp.database.impl.source.MessagesLocalSourceImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class MessagesLocalSourceTest {

    private val dao: MessageDao = mockk(relaxed = true)
    private lateinit var source: MessagesLocalSource

    @Before
    fun setup() {
        source = MessagesLocalSourceImpl(dao)
    }

    @Test
    fun `insert message`() = runTest {
        val domain = MessageDomain(
            messageId = "1",
            message = "hello",
            senderId = 1,
            timestamp = 123,
            isRead = false
        )
        val expectedEntity = domain.toEntity()

        coEvery { dao.insertMessage(expectedEntity) } just Runs

        source.insertMessage(domain)

        coVerify(exactly = 1) { dao.insertMessage(expectedEntity) }
    }

    @Test
    fun `get all messages`() = runTest {
        val entityList = listOf(
            MessageEntity(
                messageId = "1",
                message = "hello",
                senderId = 1,
                timestamp = 123,
                isRead = false
            )
        )
        val expectedDomainList = entityList.map { it.toDomain() }

        every { dao.getAllMessages() } returns flowOf(entityList)


        val result = source.getAllMessages().first()

        assertThat(result).isEqualTo(expectedDomainList)

        verify(exactly = 1) { dao.getAllMessages() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
