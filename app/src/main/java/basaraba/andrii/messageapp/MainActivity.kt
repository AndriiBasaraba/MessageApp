package basaraba.andrii.messageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import basaraba.andrii.messageapp.ui.chat.ChatScreen
import basaraba.andrii.messageapp.ui.theme.MessageAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageAppTheme {
                ChatScreen() {
                    finish()
                }
            }
        }
    }
}
