package com.sadondev.thamanya_assignment.data.remote

import com.sadondev.thamanya_assignment.data.remote.model.ContentSearchRemote
import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen

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

    override fun searchContent(text: String?): Flow<ContentSearchRemote> = flow {
        emit(client.get(SEARCH_BASE_URL).body<ContentSearchRemote>())
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
        const val SEARCH_BASE_URL = "https://mock.apidog.com/m1/735111-711675-default/search"
    }
}

