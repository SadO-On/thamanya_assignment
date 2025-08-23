package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioBookRemote(
    @SerialName("audiobook_id") val id: String,
    val name: String,
    @SerialName("author_name") val authorName: String? = null,
    val description: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    val duration: Long? = null,
    val language: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    val score: Double? = null,
)
