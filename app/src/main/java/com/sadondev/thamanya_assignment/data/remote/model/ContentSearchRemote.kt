package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class ContentSearchRemote(
    val sections: List<SearchSectionRemote>,
)
