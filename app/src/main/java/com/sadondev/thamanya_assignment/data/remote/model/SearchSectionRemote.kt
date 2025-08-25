package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchSectionRemote(
    val name: String,
    val type: String,
    val contentType: String?,
    val order: String,
    val content: List<SearchResultRemote>,
)