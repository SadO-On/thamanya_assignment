package com.sadondev.thamanya_assignment.ui.mapper


import com.sadondev.thamanya_assignment.domain.models.*
import com.sadondev.thamanya_assignment.ui.models.UiCard
import com.sadondev.thamanya_assignment.ui.models.UiSection
import kotlin.math.abs

/** Public entry point */
fun List<Section>.toUiSections(): List<UiSection> =
    this.map { it.toUiSection() }.sortedBy { it.order }

/** Map a single Section.* to the right UiSection.* wrapper and UiCards */
private fun Section.toUiSection(): UiSection = when (this) {
    is Section.Podcasts -> {
        val items = items.map { it.toCard() }
        when (layout) {
            LayoutType.SQUARE -> UiSection.Square(name, layout, order, items)
            LayoutType.GRID_2_LINES -> UiSection.Grid2Lines(name, layout, order, items)
            LayoutType.BIG_SQUARE -> UiSection.BigSquare(name, layout, order, items)
            LayoutType.QUEUE -> UiSection.Queue(name, layout, order, items)
            else -> UiSection.Unknown(name, layout, order)
        }
    }

    is Section.Episodes -> {
        val items = items.map { it.toCard() }
        when (layout) {
            LayoutType.SQUARE -> UiSection.Square(name, layout, order, items)
            LayoutType.GRID_2_LINES -> UiSection.Grid2Lines(name, layout, order, items)
            LayoutType.BIG_SQUARE -> UiSection.BigSquare(name, layout, order, items)
            LayoutType.QUEUE -> UiSection.Queue(name, layout, order, items)
            else -> UiSection.Unknown(name, layout, order)
        }
    }

    is Section.AudioBooks -> {
        val items = items.map { it.toCard() }
        when (layout) {
            LayoutType.SQUARE -> UiSection.Square(name, layout, order, items)
            LayoutType.GRID_2_LINES -> UiSection.Grid2Lines(name, layout, order, items)
            LayoutType.BIG_SQUARE -> UiSection.BigSquare(name, layout, order, items)
            LayoutType.QUEUE -> UiSection.Queue(name, layout, order, items)
            else -> UiSection.Unknown(name, layout, order)
        }
    }

    is Section.AudioArticles -> {
        val items = items.map { it.toCard() }
        when (layout) {
            LayoutType.SQUARE -> UiSection.Square(name, layout, order, items)
            LayoutType.GRID_2_LINES -> UiSection.Grid2Lines(name, layout, order, items)
            LayoutType.BIG_SQUARE -> UiSection.BigSquare(name, layout, order, items)
            LayoutType.QUEUE -> UiSection.Queue(name, layout, order, items)
            else -> UiSection.Unknown(name, layout, order)
        }
    }

    is Section.Unknown -> UiSection.Unknown(name, layout, order)
}

/** Item mappers → a single UiCard type */
private fun Podcast.toCard() = UiCard(
    id = id,
    title = title,
    subtitle = listOfNotNull(
        episodeCount?.let { "$it Ep" },
    ).joinToString(" • ").ifBlank { null },
    imageUrl = imageUrl,
    durationText = duration.toDurationText(), // some APIs give podcast-level playtime; keep if present
)

private fun Episode.toCard() = UiCard(
    id = id,
    title = title,
    subtitle = podcastName?.ifBlank { null } ?: authorName,
    imageUrl = imageUrl,
    durationText = duration.toDurationText(),
    audioUrl = audioUrl
)

private fun AudioBook.toCard() = UiCard(
    id = id,
    title = title,
    subtitle = authorName,
    imageUrl = imageUrl,
    durationText = duration.toDurationText()
)

private fun AudioArticle.toCard() = UiCard(
    id = id,
    title = title,
    subtitle = authorName,
    imageUrl = imageUrl,
    durationText = duration.toDurationText()
)

/** Utility: 3661 -> "1h 01m", 75 -> "1m 15s", null -> null */
private fun Long?.toDurationText(): String? {
    val total = this ?: return null
    val seconds = abs(total)
    val h = seconds / 3600
    val m = (seconds % 3600) / 60
    val s = seconds % 60
    return when {
        h > 0 -> String.format("%dh %02dm", h, m)
        m > 0 -> String.format("%dm %02ds", m, s)
        else -> "${s}s"
    }
}
