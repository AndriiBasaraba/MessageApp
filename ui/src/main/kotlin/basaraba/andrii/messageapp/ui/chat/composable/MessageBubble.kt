package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.chat.model.Message
import basaraba.andrii.messageapp.ui.utils.myBubbleColor

@Composable
internal fun MessageBubble(message: Message.Bubble) {
    if (message.isMyMessage) MyBubble(message = message) else OtherBubble(message = message)

    if (!message.isSmallSpacing) {
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun MyBubble(message: Message.Bubble) {
    val bubbleMaxWidth = rememberMaxBubbleWidth()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box {
            Text(
                modifier = Modifier
                    .widthIn(max = bubbleMaxWidth)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp
                        )
                    )
                    .background(myBubbleColor)
                    .padding(12.dp),
                text = message.message,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 4.dp, bottom = 2.dp)
                    .size(12.dp),
                imageVector = Icons.Default.DoneAll,
                contentDescription = null,
                tint = if (message.isRead) Color.Yellow else Color.LightGray
            )
        }
    }
}

@Composable
private fun OtherBubble(message: Message.Bubble) {
    val bubbleMaxWidth = rememberMaxBubbleWidth()
    Text(
        modifier = Modifier
            .widthIn(max = bubbleMaxWidth)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(Color(0xFFF4F5FB))
            .padding(12.dp),
        text = message.message,
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun rememberMaxBubbleWidth(): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return remember(screenWidth) {
        screenWidth * 0.7f - 16.dp
    }
}


@Preview(showBackground = true)
@Composable
private fun MessageMyBubblePreview() {
    val message = Message.Bubble(
        id = "1",
        message = "Hello",
        senderId = 1,
        timestamp = 1,
        isRead = false,
        isMyMessage = true,
        isSmallSpacing = true
    )

    MessageBubble(message)
}

@Preview(showBackground = true)
@Composable
private fun MessageOtherBubblePreview() {
    val message = Message.Bubble(
        id = "1",
        message = "Hello",
        senderId = 1,
        timestamp = 1,
        isRead = false,
        isMyMessage = false,
        isSmallSpacing = true
    )

    MessageBubble(message)
}
