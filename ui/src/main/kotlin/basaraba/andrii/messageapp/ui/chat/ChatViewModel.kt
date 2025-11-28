package basaraba.andrii.messageapp.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import basaraba.andrii.messageapp.domain.contract.use_case.GetAllMessagesUseCase
import basaraba.andrii.messageapp.domain.contract.use_case.SendMessageUseCase
import basaraba.andrii.messageapp.ui.chat.mapper.MessageUiMapper
import basaraba.andrii.messageapp.ui.chat.model.ActiveUser
import basaraba.andrii.messageapp.ui.chat.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ChatViewModel(
    getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val uiMapper: MessageUiMapper
) : ViewModel() {

    private val _activeUser = MutableStateFlow(ActiveUser.SARAH)
    val activeUser: StateFlow<ActiveUser> = _activeUser.asStateFlow()

    val messagesUi: StateFlow<List<Message>> = combine(
        getAllMessagesUseCase(),
        activeUser
    ) { messages, user ->
        uiMapper.map(messages, user.id)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sendMessageUseCase(message = message, senderId = activeUser.value.id)
        }
    }

    fun changeActiveUser() {
        _activeUser.update { if (it == ActiveUser.JAMES) ActiveUser.SARAH else ActiveUser.JAMES }
    }
}
