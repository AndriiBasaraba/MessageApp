package basaraba.andrii.messageapp.ui.utils

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
internal fun Long.formatDate(): Pair<String, String> {
    val timePattern = "HH:mm"
    val dayPattern = "EEEE"

    return try {
        val instant = Instant.fromEpochMilliseconds(this)
        val local = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val timeFormatter = DateTimeFormatter.ofPattern(timePattern)
        val dayFormatter = DateTimeFormatter.ofPattern(dayPattern)


        return dayFormatter.format(local.toJavaLocalDateTime()) to
                timeFormatter.format(local.toJavaLocalDateTime())
    } catch (e: Exception) {
        e.printStackTrace()
        "" to ""
    }
}

val redColor = Color(0xFFFB4474)
val myBubbleColor = Color(0xFFFC2375)
