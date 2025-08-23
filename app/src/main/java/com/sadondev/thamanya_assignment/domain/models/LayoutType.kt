package com.sadondev.thamanya_assignment.domain.models

enum class LayoutType {
    SQUARE,
    GRID_2_LINES,
    BIG_SQUARE,
    QUEUE,
    UNKNOWN;

    companion object {
        fun fromRemote(raw: String): LayoutType = when (raw.trim().lowercase()) {
            "square" -> SQUARE
            "2_lines_grid", "2-lines-grid", "two_lines_grid" -> GRID_2_LINES
            "big_square", "big square" -> BIG_SQUARE
            "queue" -> QUEUE
            else -> UNKNOWN
        }
    }
}
