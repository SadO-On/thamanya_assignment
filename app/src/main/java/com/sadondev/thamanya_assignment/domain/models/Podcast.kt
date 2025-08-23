package com.sadondev.thamanya_assignment.domain.models

data class Podcast(
    val id: String,
    val title: String,
    val descriptionHtml: String?,
    val imageUrl: String?,
    val episodeCount: Int?,
    val duration: Long?,   // In Sec
    val language: String?,
    val priority: Int?,
    val popularityScore: Int?,
    val score: Double?,
)
