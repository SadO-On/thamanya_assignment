package com.sadondev.thamanya_assignment.domain.models

sealed class Section {
    abstract val name: String
    abstract val layout: LayoutType
    abstract val order: Int

    data class Podcasts(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        val items: List<Podcast>,
    ) : Section()

    data class Episodes(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        val items: List<Episode>,
    ) : Section()

    data class AudioBooks(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        val items: List<AudioBook>,
    ) : Section()

    data class AudioArticles(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
        val items: List<AudioArticle>,
    ) : Section()

    /** If the backend adds a new content_type. **/
    data class Unknown(
        override val name: String,
        override val layout: LayoutType,
        override val order: Int,
    ) : Section()
}
