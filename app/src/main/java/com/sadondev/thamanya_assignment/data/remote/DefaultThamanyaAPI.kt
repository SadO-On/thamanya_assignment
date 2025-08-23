package com.sadondev.thamanya_assignment.data.remote

import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen

class DefaultThamanyaAPI(private val client: HttpClient) : ThamanyaAPI {
    override fun getMainContent(): Flow<MainContentRemote> = flow {
        val payload: MainContentRemote = client.get(MAIN_CONTENT_URL).body()
        emit(payload)
    }.retryWhen { cause, attempt ->
        (cause is java.io.IOException || cause !is kotlinx.coroutines.CancellationException) &&
                attempt < 3
    }.catch { e ->
        throw e
    }.flowOn(Dispatchers.IO)
}

const val MAIN_CONTENT_URL = "https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections"