package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PodcastRemote(
    @SerialName("podcast_id") val id: String,
    val name: String,
    val description: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("episode_count") val episodeCount: Int? = null,
    val duration: Long? = null,
    val language: String? = null,
    val priority: Int? = null,
    val popularityScore: Int? = null,
    val score: Double? = null,
)