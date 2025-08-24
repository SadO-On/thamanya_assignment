package com.sadondev.thamanya_assignment.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorClient {

    fun getClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        coerceInputValues = true
                        isLenient = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.ANDROID // Use it DEFAULT for KMP Later
                level = LogLevel.BODY
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
                requestTimeoutMillis = 15_000
            }

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTP
                    contentType(ContentType.Application.Json)
                }
            }
        }
    }
}