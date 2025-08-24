package com.sadondev.thamanya_assignment.domain.mapper

import com.sadondev.thamanya_assignment.data.remote.model.AudioArticleRemote
import com.sadondev.thamanya_assignment.data.remote.model.AudioBookRemote
import com.sadondev.thamanya_assignment.data.remote.model.ContentType
import com.sadondev.thamanya_assignment.data.remote.model.EpisodeRemote
import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import com.sadondev.thamanya_assignment.data.remote.model.PodcastRemote
import com.sadondev.thamanya_assignment.data.remote.model.SectionRemote
import com.sadondev.thamanya_assignment.domain.models.AudioArticle
import com.sadondev.thamanya_assignment.domain.models.AudioBook
import com.sadondev.thamanya_assignment.domain.models.Episode
import com.sadondev.thamanya_assignment.domain.models.LayoutType
import com.sadondev.thamanya_assignment.domain.models.MainContent
import com.sadondev.thamanya_assignment.domain.models.Pagination
import com.sadondev.thamanya_assignment.domain.models.Podcast
import com.sadondev.thamanya_assignment.domain.models.Section
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

internal inline fun <reified T> List<JsonObject>.decodeList(
    json: Json = DefaultJson
): List<T> = map { json.decodeFromJsonElement<T>(it) }

// ---------- Item mappers ----------
internal fun PodcastRemote.toDomain() = Podcast(
    id = id,
    title = name,
    descriptionHtml = description,
    imageUrl = avatarUrl,
    episodeCount = episodeCount,
    duration = duration,
    language = language,
    priority = priority,
    popularityScore = popularityScore,
    score = score
)

internal fun EpisodeRemote.toDomain() = Episode(
    id = id,
    title = name,
    episodeType = episodeType,
    podcastName = podcastName,
    authorName = authorName,
    descriptionHtml = description,
    number = number,
    duration = duration,
    imageUrl = avatarUrl,
    audioUrl = audioUrl,
    releaseDateIso = releaseDate,
    podcastId = podcastId,
    score = score,
    podcastPriority = podcastPriority,
    podcastPopularityScore = podcastPopularityScore
)

internal fun AudioBookRemote.toDomain() = AudioBook(
    id = id,
    title = name,
    authorName = authorName,
    descriptionHtml = description,
    imageUrl = avatarUrl,
    duration = duration,
    language = language,
    releaseDateIso = releaseDate,
    score = score
)

internal fun AudioArticleRemote.toDomain() = AudioArticle(
    id = id,
    title = name,
    authorName = authorName,
    descriptionHtml = description,
    imageUrl = avatarUrl,
    duration = duration,
    releaseDateIso = releaseDate,
    score = score
)



// ---------- Section mapper ----------
internal fun SectionRemote.toDomain(json: Json = DefaultJson): Section {
    val layout = LayoutType.fromRemote(type)
    return when (contentType) {
        ContentType.PODCAST -> {
            val items = content.decodeList<PodcastRemote>(json)
                .map { it.toDomain() }
                .distinctBy { it.id }
            Section.Podcasts(name = name, layout = layout, order = order, items = items)
        }

        ContentType.EPISODE -> {
            val items = content.decodeList<EpisodeRemote>(json)
                .map { it.toDomain() }
                .distinctBy { it.id }
            Section.Episodes(name = name, layout = layout, order = order, items = items)
        }

        ContentType.AUDIO_BOOK -> {
            val items = content.decodeList<AudioBookRemote>(json)
                .map { it.toDomain() }
                .distinctBy { it.id }
            Section.AudioBooks(name = name, layout = layout, order = order, items = items)
        }

        ContentType.AUDIO_ARTICLE -> {
            val items = content.decodeList<AudioArticleRemote>(json)
                .map { it.toDomain() }
                .distinctBy { it.id }
            Section.AudioArticles(name = name, layout = layout, order = order, items = items)
        }

        else -> {
            val items = content.decodeList<PodcastRemote>(json)
                .map { it.toDomain() }
                .distinctBy { it.id }
            Section.Podcasts(name = name, layout = layout, order = order, items = items)

        }
    }
}

fun MainContentRemote.toDomain(json: Json = DefaultJson): MainContent {
    val domainSections = sections
        .map {
            runCatching { it.toDomain(json) }.getOrElse { _ ->
                Section.Unknown(
                    it.name,
                    LayoutType.fromRemote(it.type),
                    it.order,
                )
            }
        }
        .sortedBy { it.order }

    return MainContent(
        sections = domainSections,
        pagination = pagination?.let {
            Pagination(
                nextPage = it.nextPage,
                totalPages = it.totalPages
            )
        }
    )
}