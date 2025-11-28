package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.chat.model.Message
import basaraba.andrii.messageapp.ui.utils.myBubbleColor

@Composable
internal fun MessageBubble(message: Message.Bubble) {
    Column {
        if (message.isMyMessage) MyBubble(message = message) else OtherBubble(message = message)

        if (!message.isSmallSpacing) {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MyBubble(message: Message.Bubble) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .widthIn(max = getMaximumBubbleWidth())
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp
                    )
                )
                .background(myBubbleColor)
                .padding(vertical = 8.dp, horizontal = 12.dp),
            text = message.message,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun OtherBubble(message: Message.Bubble) {
    Text(
        modifier = Modifier
            .widthIn(max = getMaximumBubbleWidth())
            .clip(
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomEnd = 12.dp
                )
            )
            .background(Color(0xFFF4F5FB))
            .padding(vertical = 8.dp, horizontal = 12.dp),
        text = message.message,
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge
    )
}


@Composable
private fun getMaximumBubbleWidth(): Dp {
    val windowInfo = LocalWindowInfo.current
    val containerWidthPx = windowInfo.containerSize.width
    val density = LocalDensity.current

    return with(density) {
        val marginPx = 16.dp.toPx()
        ((containerWidthPx - marginPx) * 0.7f).toDp()
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
