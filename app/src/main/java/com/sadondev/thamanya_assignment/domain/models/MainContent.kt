package com.sadondev.thamanya_assignment.domain.models

data class MainContent(
    val sections: List<Section>,
    val pagination: Pagination? = null,
)




data class Pagination(
    val nextPage: String?,
    val totalPages: Int?,
)
