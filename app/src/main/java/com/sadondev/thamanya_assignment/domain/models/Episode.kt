package com.sadondev.thamanya_assignment.domain.models

data class Episode(
    val id: String,
    val title: String,
    val episodeType: String?,
    val podcastName: String?,
    val authorName: String?,
    val descriptionHtml: String?,
    val number: Int?,
    val duration: Long?,// In Sec
    val imageUrl: String?,
    val audioUrl: String?,
    val releaseDateIso: String?,
    val podcastId: String?,
    val score: Double?,
    val podcastPriority: Int?,
    val podcastPopularityScore: Int?,
)