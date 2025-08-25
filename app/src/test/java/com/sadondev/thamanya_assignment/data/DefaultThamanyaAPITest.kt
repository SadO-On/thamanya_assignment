package com.sadondev.thamanya_assignment.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sadondev.thamanya_assignment.data.remote.ThamanyaAPI
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.getKoin
import java.io.IOException

class DefaultThamanyaAPITest {

    @After
    fun tearDown() {
        // ensure a clean Koin between tests
        stopKoin()
    }

    @Test
    fun `first page hits base endpoint and parses`() = runTest {
        val page1 = loadJson("main_content_page1.json")

        val engine = MockEngine { request ->
            assertThat(request.url.fullPath).isEqualTo("/home_sections")
            respond(content = page1, headers = jsonHeaders)
        }

        // start Koin for THIS test
        startKoin { modules(testModule(engine)) }

        val api: ThamanyaAPI = getKoin().get()

        api.getMainContent(pagePath = null).test {
            val remote = awaitItem()
            assertThat(remote.sections).isNotEmpty()
            assertThat(remote.pagination?.nextPage).isNotNull()
            awaitComplete()
        }
    }

    @Test
    fun `relative pagePath is converted to absolute url`() = runTest {
        val page2 = loadJson("main_content_page2.json")
        var hit = false

        val engine = MockEngine { request ->
            hit = true
            assertThat(request.url.fullPath).isEqualTo("/home_sections?page=2")
            respond(content = page2, headers = jsonHeaders)
        }

        startKoin { modules(testModule(engine)) }

        val api: ThamanyaAPI = getKoin().get()

        api.getMainContent(pagePath = "/home_sections?page=2").test {
            awaitItem()
            awaitComplete()
        }
        assertThat(hit).isTrue()
    }

    @Test
    fun `retries up to 3 times on IOException then succeeds`() = runTest {
        val page1 = loadJson("main_content_page1.json")
        var calls = 0

        val engine = MockEngine {
            calls++
            if (calls < 3) throw IOException("boom $calls")
            respond(content = page1, headers = jsonHeaders)
        }

        startKoin { modules(testModule(engine)) }

        val api: ThamanyaAPI = getKoin().get()

        api.getMainContent().test {
            awaitItem()
            awaitComplete()
        }
        assertThat(calls).isEqualTo(3)
    }
}

// ---- helpers ----

// Put files under src/test/resources (not res/).
fun loadJson(path: String): String {
    val stream = requireNotNull(
        object {}.javaClass.classLoader?.getResourceAsStream(path)
    ) { "Test resource not found: $path. Expected under src/test/resources." }
    return stream.bufferedReader(Charsets.UTF_8).use { it.readText() }
}

val jsonHeaders = headersOf(HttpHeaders.ContentType, "application/json")
