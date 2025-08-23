package com.sadondev.thamanya_assignment.ui.dashboard

import android.R.attr.contentDescription
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sadondev.thamanya_assignment.R
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.Avatar
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.SectionsRowWidget
import com.sadondev.thamanya_assignment.ui.theme.Neutral90
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme


@Composable
fun DashboardScreen() {
    DashboardContent()
}


@Composable
private fun DashboardContent() {
    var selected by remember { mutableIntStateOf(3) }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Avatar(
                        url = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                        contentDescription = stringResource(R.string.profile_image_content_description)
                    )
                    Text("Welcome, Mohammed")
                }
                SectionsRowWidget(
                    sections = listOf("All", "Design", "Tech", "Games", "Offers", "News", "Wallet"),
                    selectedIndex = selected,
                    onSectionSelected = { selected = it }
                )
            }


        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

        }
    }
}


@Preview
@Composable
private fun DashboardContentPreview() {
    ThamanyaAssignmentTheme(darkTheme = false) {
        Surface { DashboardContent() }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, apiLevel = 35,

    )
@Composable
private fun DashboardContentDarkModePreview() {
    ThamanyaAssignmentTheme(darkTheme = true) {
        Surface { DashboardContent() }
    }
}