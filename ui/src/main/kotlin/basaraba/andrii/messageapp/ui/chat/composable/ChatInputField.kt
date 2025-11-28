package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.utils.redColor

@Composable
internal fun ChatInputField(
    sendNewMessage: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var query by remember { mutableStateOf("") }

    fun sendMessage() {
        if (query.isEmpty()) return

        sendNewMessage(query)
        query = ""
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(1.dp)
            .padding(vertical = 16.dp)
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = query,
            onValueChange = { query = it },
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
                onSend = {
                    sendMessage()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.width(16.dp))

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

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatInputFieldPreview() {
    ChatInputField {}
}
