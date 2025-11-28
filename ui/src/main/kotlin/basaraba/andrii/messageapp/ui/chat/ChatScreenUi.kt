package basaraba.andrii.messageapp.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.chat.composable.ChatInputField
import basaraba.andrii.messageapp.ui.chat.composable.ChatTopBar
import basaraba.andrii.messageapp.ui.chat.composable.DateHeader
import basaraba.andrii.messageapp.ui.chat.composable.MessageBubble
import basaraba.andrii.messageapp.ui.chat.model.ActiveUser
import basaraba.andrii.messageapp.ui.chat.model.Message

@Composable
internal fun ChatScreenUi(
    messages: List<Message>,
    activeUser: ActiveUser,
    sendNewMessage: (String) -> Unit,
    changeActiveUser: () -> Unit,
    onBackClick: () -> Unit,
) {
    val listState = rememberLazyListState()

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            ChatTopBar(
                activeUser = activeUser,
                onBackClick = onBackClick,
                changeActiveUser = changeActiveUser
            )
        },
        bottomBar = {
            ChatInputField(sendNewMessage = sendNewMessage)
        }
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(messages, key = { it.id }) {
                when (it) {
                    is Message.Header -> DateHeader(header = it)
                    is Message.Bubble -> MessageBubble(message = it)
                }
            }
        }
    }

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }
}
