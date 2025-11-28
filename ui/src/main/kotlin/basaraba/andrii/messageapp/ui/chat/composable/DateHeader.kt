package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.chat.model.Message

@Composable
internal fun DateHeader(
    header: Message.Header
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = header.day,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = header.time,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateHeaderPreview() {
    DateHeader(Message.Header("", "Monday", "11:11"))
}
