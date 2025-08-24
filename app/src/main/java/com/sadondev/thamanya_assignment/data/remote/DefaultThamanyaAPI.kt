package com.sadondev.thamanya_assignment.data.remote

import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import com.sadondev.thamanya_assignment.domain.mapper.toDomain
import com.sadondev.thamanya_assignment.domain.models.MainContent
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import kotlinx.serialization.json.Json

class DefaultThamanyaAPI(
    private val client: HttpClient,
) : ThamanyaAPI {
    override fun getMainContent(pagePath: String?): Flow<MainContentRemote> = flow {
        val url = pagePath?.let(::toAbsoluteUrl) ?: "$BASE_URL/home_sections"
        emit(client.get(url).body<MainContentRemote>())
    }.retryWhen { cause, attempt ->
        (cause is java.io.IOException || cause !is kotlinx.coroutines.CancellationException) &&
                attempt < 3
    }.catch { e ->
        throw e
    }.flowOn(Dispatchers.IO)


    private fun toAbsoluteUrl(path: String): String =
        if (path.startsWith("http")) path
        else BASE_URL.trimEnd('/') + "/" + path.trimStart('/')

    private companion object {
        const val BASE_URL = "https://api-v2-b2sit6oh3a-uc.a.run.app"
    }
}

