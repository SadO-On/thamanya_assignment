package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PaginationRemote(
    @SerialName("next_page") val nextPage: String? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
)