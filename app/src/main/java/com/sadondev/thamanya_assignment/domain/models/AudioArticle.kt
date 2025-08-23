package com.sadondev.thamanya_assignment.domain.models

data class AudioArticle(
    val id: String,
    val title: String,
    val authorName: String?,
    val descriptionHtml: String?,
    val imageUrl: String?,
    val duration: Long?,
    val releaseDateIso: String?,
    val score: Double?,
)