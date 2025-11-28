package basaraba.andrii.messageapp.ui.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(
    onBackClick: () -> Unit
) {
    val viewModel: ChatViewModel = koinViewModel()

    val state by viewModel.messagesUi.collectAsStateWithLifecycle()

    val activeUser by viewModel.activeUser.collectAsStateWithLifecycle()

    ChatScreenUi(
        messages = state,
        activeUser = activeUser,
        sendNewMessage = viewModel::sendMessage,
        changeActiveUser = viewModel::changeActiveUser,
        onBackClick = onBackClick
    )
}
