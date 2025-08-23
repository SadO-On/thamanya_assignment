package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sadondev.thamanya_assignment.R
import com.sadondev.thamanya_assignment.ui.theme.Neutral90

@Composable
fun Avatar(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val isPreview = LocalInspectionMode.current
    val base = modifier
        .size(48.dp)
        .clip(CircleShape)
        .background(color = Color.White, shape = CircleShape)
        .border(1.dp, color = Neutral90, shape = CircleShape)

    if (isPreview) {
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = base
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = base
        )
    }
}
