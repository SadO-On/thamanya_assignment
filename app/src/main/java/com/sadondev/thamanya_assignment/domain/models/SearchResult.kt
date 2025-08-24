package com.sadondev.thamanya_assignment.domain.models

import kotlinx.serialization.SerialName

data class SearchResult(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val episodeCount: Int?,
    val duration: String?,
    val language: String?,
)
