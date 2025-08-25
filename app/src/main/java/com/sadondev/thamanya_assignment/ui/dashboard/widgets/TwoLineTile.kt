package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sadondev.thamanya_assignment.formatTime
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme


@Composable
fun TwoLineTile(
    imageUrl: String,
    title: String,
    subtitle: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SquareImageWidget(
            size = 150,
            contentDescription = title,
            imageUrl = imageUrl
        )
        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        if (!subtitle.isNullOrBlank()) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.60f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TwoLineTilePreview() {
    ThamanyaAssignmentTheme(darkTheme = false, dynamicColor = false) {
        Surface {
            TwoLineTile(
                imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                title = "The Big Listen",
                subtitle = "60 Ep"
            )
        }
    }
}

@Preview(
    showBackground = true, apiLevel = 35,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun TwoLineTilePreviewDarkMode() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        Surface {
            TwoLineTile(
                imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                title = "The Big Listen",
                subtitle = "60 Ep"
            )
        }
    }
}