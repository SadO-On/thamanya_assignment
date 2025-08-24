package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sadondev.thamanya_assignment.ui.models.UiCard


@Composable
fun SquareGrid(uiCards: List<UiCard>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(uiCards.size) { i ->
            SquareWidget(
                modifier = Modifier,
                imageUrl = uiCards[i].imageUrl,
                title =  uiCards[i].title,
                duration =  uiCards[i].durationText ?: "",
                info = uiCards[i].subtitle.toString()
            )
        }
    }
}