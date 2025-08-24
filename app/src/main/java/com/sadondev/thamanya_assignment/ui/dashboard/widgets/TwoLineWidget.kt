package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadondev.thamanya_assignment.formatTime
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme


@Composable
fun QueueWidget(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    duration: String,
    info: String,
) {

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        SquareImageWidget(
            size = 87,
            contentDescription = title,
            imageUrl = imageUrl ?: ""
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(info)
            Text(
                title, fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeChip(duration)
                IconButton(onClick = {}, modifier = Modifier) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                        contentDescription = "Add to playlist",
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                }
                IconButton(onClick = {}, modifier = Modifier) {

                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "Add to playlist",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun QueueWidgetWidgetPreview() {
    ThamanyaAssignmentTheme(darkTheme = false, dynamicColor = false) {
        Surface {
            QueueWidget(
                imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                title = "The Big Listen",
                duration = formatTime(266195L),
                info = "60 Ep"
            )
        }
    }
}

@Preview(
    showBackground = true, apiLevel = 35,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun QueueWidgetWidgetPreviewDarkMode() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        Surface {
            QueueWidget(
                imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                title = "The Big Listen",
                duration = formatTime(266195L),
                info = "60 Ep"
            )
        }
    }
}