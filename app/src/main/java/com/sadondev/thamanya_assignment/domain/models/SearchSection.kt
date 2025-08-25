package com.sadondev.thamanya_assignment.domain.models


data class SearchSection(
    val name: String,
    val type: String,
    val contentType: String?,
    val order: String,
    val content: List<SearchResult>,
)
