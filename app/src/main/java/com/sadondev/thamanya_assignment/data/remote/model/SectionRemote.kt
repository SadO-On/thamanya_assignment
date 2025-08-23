package com.sadondev.thamanya_assignment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import com.sadondev.thamanya_assignment.data.remote.model.ContentType

@Serializable
data class SectionRemote(
    val name: String,
    // Backend uses mixed values like "square", "2_lines_grid", "big_square", and even "big square".
    // Keep it a String for maximum tolerance.
    val type: String,
    @SerialName("content_type")
    val contentType: ContentType,
    val order: Int,
    val content: List<JsonObject>,
)


inline fun <reified T> SectionRemote.decodeContentAs(
    json: Json = Json { ignoreUnknownKeys = true }
): List<T> = content.map { json.decodeFromJsonElement<T>(it) }
