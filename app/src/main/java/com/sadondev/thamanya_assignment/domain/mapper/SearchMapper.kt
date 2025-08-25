package com.sadondev.thamanya_assignment.domain.mapper

import com.sadondev.thamanya_assignment.data.remote.model.ContentSearchRemote
import com.sadondev.thamanya_assignment.data.remote.model.SearchResultRemote
import com.sadondev.thamanya_assignment.data.remote.model.SearchSectionRemote
import com.sadondev.thamanya_assignment.domain.models.ContentSearch
import com.sadondev.thamanya_assignment.domain.models.SearchResult
import com.sadondev.thamanya_assignment.domain.models.SearchSection
import com.sadondev.thamanya_assignment.formatTime


internal fun ContentSearchRemote.toDomain(): ContentSearch {
    return ContentSearch(
        sections = sections.map { it.toDomain() }
    )
}

internal fun SearchSectionRemote.toDomain(): SearchSection {
    return SearchSection(
        name = name,
        type = type,
        contentType = contentType,
        order = order,
        content = content.map { it.toDomain() }
    )
}


internal fun SearchResultRemote.toDomain(): SearchResult {
    return SearchResult(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        episodeCount = episodeCount,
        duration = formatTime(duration?.toLong() ?: 0L),
        language = language
    )
}