package basaraba.andrii.domain.impl.use_case

import basaraba.andrii.domain.model.MessageDomain
import basaraba.andrii.messageapp.data.contract.repository.MessagedRepository
import basaraba.andrii.messageapp.domain.contract.use_case.SendMessageUseCase
import basaraba.andrii.messageapp.domain.impl.use_case.SendMessageUseCaseImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SendMessageUseCaseTest {

    private val repository = mockk<MessagedRepository>()
    private lateinit var useCase: SendMessageUseCase

    @Before
    fun setup() {
        useCase = SendMessageUseCaseImpl(repository)
    }

    @Test
    fun testUseCase() = runTest {
        val newMessage = MessageDomain(
            messageId = "new_id",
            message = "A new message",
            senderId = 2L,
            timestamp = System.currentTimeMillis(),
            isRead = true
        )

        coEvery { repository.insertMessage(newMessage) } just Runs

        repository.insertMessage(newMessage)

        coVerify(exactly = 1) { repository.insertMessage(newMessage) }
    }

}
