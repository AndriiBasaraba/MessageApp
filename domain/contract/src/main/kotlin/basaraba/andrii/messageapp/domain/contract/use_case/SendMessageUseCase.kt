package basaraba.andrii.messageapp.domain.contract.use_case

interface SendMessageUseCase {
    suspend operator fun invoke(message: String, senderId: Long)
}
