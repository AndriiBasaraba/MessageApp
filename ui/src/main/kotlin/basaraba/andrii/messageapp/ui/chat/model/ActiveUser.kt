package basaraba.andrii.messageapp.ui.chat.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import basaraba.andrii.messageapp.ui.R

internal enum class ActiveUser(
    val id: Long,
    @param:StringRes val value: Int,
    @param:DrawableRes val icon: Int
) {
    SARAH(id = 1, value = R.string.sarah, icon = R.drawable.ic_avatar_sarah),
    JAMES(id = 2, value = R.string.james, icon = R.drawable.ic_avatar_james)
}
