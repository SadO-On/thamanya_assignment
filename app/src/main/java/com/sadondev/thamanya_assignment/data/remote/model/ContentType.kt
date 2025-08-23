package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ContentType {
    @SerialName("podcast") PODCAST,
    @SerialName("episode") EPISODE,
    @SerialName("audio_book") AUDIO_BOOK,
    @SerialName("audio_article") AUDIO_ARTICLE,
}