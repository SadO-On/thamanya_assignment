package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MainContentRemote(
    val sections: List<SectionRemote>,
    val pagination: PaginationRemote? = null,
)