package basaraba.andrii.messageapp.data.impl.repository

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.database.contract.source.MessagesLocalSource
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class MessagedRepositoryTest {

    private val localSource = mockk<MessagesLocalSource>()
    private lateinit var repository: MessagedRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = MessagedRepositoryImpl(localSource = localSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `getAllMessages returns flow from local source`() = runTest {
        val expectedMessages = listOf(
            MessageDomain(
                messageId = "id1",
                message = "Test Message 1",
                senderId = 1L,
                timestamp = 1000L,
                isRead = false
            )
        )

        val messagesFlow: Flow<List<MessageDomain>> = flowOf(expectedMessages)

        every { localSource.getAllMessages() } returns messagesFlow

        val resultFlow = repository.getAllMessages()

        val result = resultFlow.first()

        assertEquals(expectedMessages, result)

        verify(exactly = 1) { localSource.getAllMessages() }
    }


    @Test
    fun `insertMessage delegates to local source`() = runTest {
        val newMessage = MessageDomain(
            messageId = "new_id",
            message = "A new message",
            senderId = 2L,
            timestamp = System.currentTimeMillis(),
            isRead = true
        )

        coEvery { localSource.insertMessage(newMessage) } just Runs

        repository.insertMessage(newMessage)

        coVerify(exactly = 1) { localSource.insertMessage(newMessage) }
    }
}
