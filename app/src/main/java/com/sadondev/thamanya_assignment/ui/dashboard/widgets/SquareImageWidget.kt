package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sadondev.thamanya_assignment.R


@Composable
fun SquareImageWidget(
    modifier: Modifier = Modifier,
    size: Int,
    contentDescription: String,
    imageUrl: String
) {
    val isPreview = LocalInspectionMode.current
    val base = modifier
        .size(size.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color = Color.White, shape = RoundedCornerShape(8.dp))

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
                .data(imageUrl)
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

@Preview
@Composable
private fun SquareImageWidgetPreview(){
    SquareImageWidget(
        modifier = Modifier,
        size = 180,
        contentDescription = "",
        imageUrl = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY"
    )
}