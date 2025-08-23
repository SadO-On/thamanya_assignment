package com.sadondev.thamanya_assignment.ui.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadondev.thamanya_assignment.R
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.Avatar
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.SectionsRowWidget
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    DashboardContent()

    when (uiState.value) {
        is DashboardViewState.Data -> {}
        DashboardViewState.Loading -> {}
        is DashboardViewState.Error -> {}
        else -> {}
    }
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