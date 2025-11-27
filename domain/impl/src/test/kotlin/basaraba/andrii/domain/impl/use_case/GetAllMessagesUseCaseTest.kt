package basaraba.andrii.domain.impl.use_case

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.domain.contract.use_case.GetAllMessagesUseCase
import basaraba.andrii.messageapp.domain.impl.use_case.GetAllMessagesUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetAllMessagesUseCaseTest {

    private val repository = mockk<MessagedRepository>()
    private lateinit var useCase: GetAllMessagesUseCase

    @Before
    fun setup() {
        useCase = GetAllMessagesUseCaseImpl(repository)
    }

    @Test
    fun testUseCase() = runTest {
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

        every { repository.getAllMessages() } returns messagesFlow

        val resultFlow = useCase.invoke()

        val result = resultFlow.first()

        assertEquals(expectedMessages, result)
        verify(exactly = 1) { repository.getAllMessages() }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
