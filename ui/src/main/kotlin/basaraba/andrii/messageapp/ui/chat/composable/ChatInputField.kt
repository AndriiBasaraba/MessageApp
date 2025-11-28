package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.utils.redColor

@Composable
internal fun ChatInputField(
    sendNewMessage: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    fun sendMessage() {
        if (query.isEmpty()) return

        sendNewMessage(query)
        query = ""
    }

    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier
            .shadow(10.dp)
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        MessageInputField(
            modifier = Modifier.weight(1f),
            query = query,
            updateQuery = { query = it },
            sendMessage = ::sendMessage
        )

        Spacer(modifier = Modifier.width(16.dp))

        SendBtn(query = query, sendMessage = ::sendMessage)

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
private fun MessageInputField(
    modifier: Modifier,
    query: String,
    updateQuery: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = query,
        onValueChange = { updateQuery(it) },
        singleLine = true,
        shape = RoundedCornerShape(28.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedTextColor = Color.Black,
            focusedIndicatorColor = redColor,
            unfocusedIndicatorColor = Color.LightGray,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(
            onSend = { sendMessage() }
        )
    )
}

@Composable
private fun SendBtn(
    query: String,
    sendMessage: () -> Unit,
) {
    IconButton(
        modifier = Modifier.size(48.dp),
        colors = IconButtonDefaults.iconButtonColors().copy(
            containerColor = redColor,
            disabledContainerColor = redColor.copy(alpha = 0.4f)
        ),
        onClick = {
            sendMessage()
        },
        enabled = query.isNotBlank()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.Send,
            modifier = Modifier.size(28.dp),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatInputFieldPreview() {
    ChatInputField {}
}
