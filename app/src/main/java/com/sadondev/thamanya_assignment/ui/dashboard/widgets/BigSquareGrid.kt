package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sadondev.thamanya_assignment.ui.models.UiCard
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun BigSquareGrid(
    modifier: Modifier = Modifier,
    uiCards: List<UiCard>,
    isLoadingMore: Boolean,
    nextPage: String?,
    onLoadMore: () -> Unit,
    loadMoreThreshold: Int = 12,
    gridState: LazyGridState = rememberLazyGridState(),
) {

    var lastRequestedKey by remember { mutableStateOf<String?>(null) }

    // Compute when we're "near the end"
    val shouldLoadMore by remember {
        derivedStateOf {
            val info = gridState.layoutInfo
            val total = info.totalItemsCount
            if (total == 0) return@derivedStateOf false

            val last = info.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false
            val lastVisibleIndex = last.index
            val endReached = lastVisibleIndex >= (total - loadMoreThreshold)

            val contentBottom = last.offset.y + last.size.height
            val viewportBottom = info.viewportEndOffset

            val notFillingViewport = contentBottom < viewportBottom

            endReached || notFillingViewport
        }
    }

    LaunchedEffect(gridState, nextPage) {
        snapshotFlow { shouldLoadMore }
            .distinctUntilChanged()
            .collect { should ->
                if (should && !isLoadingMore && nextPage != null && nextPage != lastRequestedKey) {
                    lastRequestedKey = nextPage
                    onLoadMore()
                }
            }
    }


    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(uiCards, key = { it.id }) { card ->
            SquareWidget(
                modifier = Modifier,
                imageUrl = card.imageUrl,
                title = card.title,
                duration = card.durationText ?: "",
                info = card.subtitle.toString()
            )
        }
        if (isLoadingMore) {
            item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(maxLineSpan) }) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}