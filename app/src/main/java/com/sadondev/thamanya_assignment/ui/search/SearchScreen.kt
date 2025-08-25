package com.sadondev.thamanya_assignment.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadondev.thamanya_assignment.domain.models.SearchSection
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.QueueWidget
import com.sadondev.thamanya_assignment.ui.search.widgets.BackSearchRowWidget
import com.sadondev.thamanya_assignment.ui.search.widgets.SearchSectionChipsWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onBackClick: () -> Unit

) {
    val uiState = viewModel.uiState.collectAsState()


    SearchContent(
        query = viewModel.query.collectAsState().value,
        onSearch = {},
        sections = (uiState.value as? SearchViewState.Data)?.value?.sections ?: emptyList(),
        onQueryChange = viewModel::onQueryChange,
        onBackClick = onBackClick
    )

}


@Composable
private fun SearchContent(
    query: String,
    sections: List<SearchSection>,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onBackClick: () -> Unit

) {

    var selected by remember { mutableIntStateOf(0) }
    val safeIndex = selected.coerceIn(0, (sections.size - 1).coerceAtLeast(0))

    Scaffold(
        topBar = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                BackSearchRowWidget(
                    query = query,
                    onQueryChange = onQueryChange,
                    onBackClick = onBackClick,
                    onSearch = onSearch
                )

                SearchSectionChipsWidget(
                    sections = sections,
                    selectedIndex = selected,
                    onSectionSelected = { selected = it }
                )
            }

        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (sections.isEmpty()) return@LazyColumn

            val section = sections[safeIndex].content
            items(section.size) { index ->
                QueueWidget(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    imageUrl = section[index].imageUrl,
                    title = section[index].name,
                    duration = section[index].duration ?: "",
                    info = section[index].episodeCount.toString()
                )
            }
        }
    }
}
