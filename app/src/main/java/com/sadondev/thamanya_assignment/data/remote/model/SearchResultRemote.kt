package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName

data class SearchResultRemote(
    @SerialName("podcast_id")
    val id: String,
    val name: String,
    val description: String?,
    @SerialName("avatar_url")
    val imageUrl: String?,
    @SerialName("episode_count")
    val episodeCount: Int?,
    val duration: String?,
    val language: String?,
)
