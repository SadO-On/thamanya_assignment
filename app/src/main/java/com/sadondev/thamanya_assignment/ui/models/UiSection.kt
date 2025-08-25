package com.sadondev.thamanya_assignment.ui.models

import com.sadondev.thamanya_assignment.domain.models.LayoutType

/** One simple card type you can render in any grid/list */
data class UiCard(
    val id: String,
    val title: String,
    val subtitle: String?,      // e.g., author/podcast name/episode count
    val imageUrl: String?,
    val durationText: String?,  // e.g., "12m", "1h 05m"
    val audioUrl: String? = null // episodes may have this
)

/** Section wrappers per layout */
sealed class UiSection {
    abstract val name: String
    abstract val layout: LayoutType
    abstract val order: Int
    abstract val items: List<UiCard>

    data class Square(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        override val items: List<UiCard>
    ) : UiSection()

    data class Grid2Lines(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        override val items: List<UiCard>
    ) : UiSection()

    data class BigSquare(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        override val items: List<UiCard>
    ) : UiSection()

    data class Queue(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        override val items: List<UiCard>
    ) : UiSection()

    data class Unknown(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int
    ) : UiSection() {
        override val items: List<UiCard> = emptyList()
    }
}

val dummy = listOf(UiSection.Square(
    name = "Test",
    layout = LayoutType.SQUARE,
    order = 1,
    items = emptyList()
))