package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeRemote(
    @SerialName("episode_id") val id: String,
    val name: String,
    @SerialName("episode_type") val episodeType: String? = null,
    @SerialName("podcast_name") val podcastName: String? = null,
    @SerialName("author_name") val authorName: String? = null,
    val description: String? = null,
    val number: Int? = null,
    val duration: Long? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("audio_url") val audioUrl: String? = null,
    @SerialName("separated_audio_url") val separatedAudioUrl: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("podcast_id") val podcastId: String? = null,
    // Various “paid_*” flags are optional
    @SerialName("paid_is_early_access") val paidIsEarlyAccess: Boolean? = null,
    @SerialName("paid_is_now_early_access") val paidIsNowEarlyAccess: Boolean? = null,
    @SerialName("paid_is_exclusive") val paidIsExclusive: Boolean? = null,
    @SerialName("paid_transcript_url") val paidTranscriptUrl: String? = null,
    @SerialName("free_transcript_url") val freeTranscriptUrl: String? = null,
    @SerialName("paid_is_exclusive_partially") val paidIsExclusivePartially: Boolean? = null,
    @SerialName("paid_exclusive_start_time") val paidExclusiveStartTime: Long? = null,
    @SerialName("paid_early_access_date") val paidEarlyAccessDate: String? = null,
    @SerialName("paid_early_access_audio_url") val paidEarlyAccessAudioUrl: String? = null,
    @SerialName("paid_exclusivity_type") val paidExclusivityType: String? = null,
    val score: Double? = null,
    // Extra metadata occasionally present
    @SerialName("podcastPriority") val podcastPriority: Int? = null,
    @SerialName("podcastPopularityScore") val podcastPopularityScore: Int? = null,
)
