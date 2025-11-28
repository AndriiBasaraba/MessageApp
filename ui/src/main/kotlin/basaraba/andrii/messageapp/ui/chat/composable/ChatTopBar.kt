package basaraba.andrii.messageapp.ui.chat.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.SwapHorizontalCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basaraba.andrii.messageapp.ui.chat.model.ActiveUser
import basaraba.andrii.messageapp.ui.utils.redColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatTopBar(
    activeUser: ActiveUser,
    onBackClick: () -> Unit,
    changeActiveUser: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.shadow(2.dp),
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = Color.White),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(activeUser.icon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(activeUser.value),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff364657)
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = redColor
                )
            }
        },
        actions = {
            IconButton(onClick = changeActiveUser) {
                Icon(
                    imageVector = Icons.Rounded.SwapHorizontalCircle,
                    contentDescription = null,
                    tint = redColor
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.MoreHoriz,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    )
}


@Preview
@Composable
private fun ChatTopBarPreview() {
    ChatTopBar(
        activeUser = ActiveUser.JAMES,
        onBackClick = {},
        changeActiveUser = {}
    )
}
