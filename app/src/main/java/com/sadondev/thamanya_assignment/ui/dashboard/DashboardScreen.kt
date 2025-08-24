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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadondev.thamanya_assignment.R
import com.sadondev.thamanya_assignment.domain.models.LayoutType.*
import com.sadondev.thamanya_assignment.domain.models.Section
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.Avatar
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.SectionsRowWidget
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.SquareGrid
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.ThemeSwitcherButton
import com.sadondev.thamanya_assignment.ui.models.UiSection
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
    isDark: Boolean,
    onToggle: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    DashboardContent(
        (uiState.value as? DashboardViewState.Data)?.data ?: emptyList(),
        isDark = isDark,
        onToggle = onToggle
    )


    when (uiState.value) {
        is DashboardViewState.Data -> {

        }

        DashboardViewState.Loading -> {

        }

        is DashboardViewState.Error -> {}
        else -> {}
    }
}


@Composable
private fun DashboardContent(
    sections: List<UiSection>,
    isDark: Boolean,
    onToggle: () -> Unit
) {
    var selected by remember { mutableIntStateOf(0) }
    val safeIndex = selected.coerceIn(0, (sections.size - 1).coerceAtLeast(0))

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Avatar(
                            url = "https://fastly.picsum.photos/id/47/200/200.jpg?hmac=dF66rvzPwuJCh4L7IjS6I0D5xrpPvqhAjbE7FstnEnY",
                            contentDescription = stringResource(R.string.profile_image_content_description)
                        )
                        Text("Welcome, Mohammed")

                    }

                    ThemeSwitcherButton(
                        isDark = isDark,
                        onToggle = onToggle,
                        modifier = Modifier
                    )
                }
                SectionsRowWidget(
                    sections = sections,
                    selectedIndex = selected,
                    onSectionSelected = { selected = it }
                )
            }


        }
    ) { innerPadding ->
        Surface(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (sections.isEmpty()) return@Surface

            when (val section = sections[safeIndex]) {
                is UiSection.Square -> SquareGrid(uiCards = section.items)
                is UiSection.Grid2Lines -> Text("Not Yet") //TwoLineGrid(items = section.items)
                is UiSection.BigSquare -> Text("Not Yet")// BigSquareGrid(items = section.items)
                is UiSection.Queue -> Text("Not Yet")// QueueList(items = section.items)
                is UiSection.Unknown -> Text("Unsupported section")
            }
        }
    }
}


@Preview
@Composable
private fun DashboardContentPreview() {
    ThamanyaAssignmentTheme(darkTheme = false) {
        Surface { DashboardContent(emptyList(), false, {}) }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, apiLevel = 35,

    )
@Composable
private fun DashboardContentDarkModePreview() {
    ThamanyaAssignmentTheme(darkTheme = true) {
        Surface { DashboardContent(emptyList(), false, {}) }
    }
}