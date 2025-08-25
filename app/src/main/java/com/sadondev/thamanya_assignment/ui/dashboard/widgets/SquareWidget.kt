package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun SquareWidget(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    duration: String,
    info: String,
) {

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SquareImageWidget(
            size = 114,
            contentDescription = title,
            imageUrl = imageUrl ?: ""
        )

        Text(
            title,
            fontSize = 11.sp,
            fontWeight = FontWeight.W500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TimeChip(duration)
            Text(
                info,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.60f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SquareWidgetPreview() {
    ThamanyaAssignmentTheme(darkTheme = false, dynamicColor = false) {
        Surface {
            SquareWidget(
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
private fun SquareWidgetPreviewDarkMode() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        Surface {
            SquareWidget(
                imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                title = "The Big Listen",
                duration = formatTime(266195L),
                info = "60 Ep"
            )
        }
    }
}