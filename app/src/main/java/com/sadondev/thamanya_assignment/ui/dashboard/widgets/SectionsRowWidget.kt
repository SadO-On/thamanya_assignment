package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sadondev.thamanya_assignment.domain.models.Section
import com.sadondev.thamanya_assignment.ui.models.UiSection
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme

/**
 * A horizontally scrollable row of single-select chips.
 */
@Composable
fun SectionsRowWidget(
    sections: List<UiSection>,
    selectedIndex: Int?,
    onSectionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    itemSpacing: Dp = 8.dp,
    shape: Shape = MaterialTheme.shapes.large, // pill-like with your theme
    scrollToSelected: Boolean = true
) {
    val listState = rememberLazyListState()

    if (scrollToSelected && selectedIndex != null) {
        LaunchedEffect(selectedIndex) {
            // Ensure the selected chip is visible when it changes
            listState.animateScrollToItem(selectedIndex.coerceAtLeast(0))
        }
    }

    LazyRow(
        state = listState,
        contentPadding = contentPadding,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(itemSpacing),
        modifier = modifier
    ) {
        itemsIndexed(
            items = sections,
            key = { index, label -> "section-$index-$label" }
        ) { index, label ->
            val selected = selectedIndex == index

            FilterChip(
                selected = selected,
                onClick = { onSectionSelected(index) },
                label = { androidx.compose.material3.Text(text = label.name) },
                leadingIcon = if (selected) {
                    { Icon(Icons.Filled.Check, contentDescription = null) }
                } else null,
                shape = shape,
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selected,
                    borderColor = MaterialTheme.colorScheme.outline,
                    selectedBorderColor = MaterialTheme.colorScheme.primary
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    }
}


@Preview(name = "SectionsRowWidget – Light", apiLevel = 35, showBackground = true)
@Composable
private fun SectionsRowWidgetPreviewLight() {
    ThamanyaAssignmentTheme(darkTheme = false, dynamicColor = false) {
        Surface {
            var selected by remember { mutableIntStateOf(0) }
            SectionsRowWidget(
                sections = emptyList(),
                selectedIndex = selected,
                onSectionSelected = { selected = it }
            )
        }
    }
}

@Preview(
    name = "SectionsRowWidget – Dark",
    apiLevel = 35,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun SectionsRowWidgetPreviewDark() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        Surface {
            var selected by remember { mutableIntStateOf(3) }
            SectionsRowWidget(
                sections = emptyList(),
                selectedIndex = selected,
                onSectionSelected = { selected = it }
            )
        }
    }
}
