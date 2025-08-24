package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sadondev.thamanya_assignment.R
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
    val isPreview = LocalInspectionMode.current
    val base = Modifier
        .size(114.dp)
        .clip(RectangleShape)
        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPreview) {
            Image(
                painter = painterResource(R.drawable.placeholder),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = base
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = base
            )
        }

        Text(
            title,
            fontSize = 11.sp,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TimeChip(duration)
            Text(info, color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.60f))
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