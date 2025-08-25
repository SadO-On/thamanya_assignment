package com.sadondev.thamanya_assignment.data


import com.sadondev.thamanya_assignment.data.remote.DefaultThamanyaAPI
import com.sadondev.thamanya_assignment.data.remote.ThamanyaAPI
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun testHttpClient(engine: MockEngine) = HttpClient(engine) {
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
}

fun testModule(engine: MockEngine) = module {
    single {
        Json {
            ignoreUnknownKeys = true; explicitNulls = false; coerceInputValues = true; isLenient =
            true
        }
    }
    single<HttpClient> { testHttpClient(engine) }
    single<ThamanyaAPI> { DefaultThamanyaAPI(get()) }
    single<ThamanyaRepository> { DefaultThamanyaRepository(get(), get()) }
}