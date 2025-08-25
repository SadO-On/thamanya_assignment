package com.sadondev.thamanya_assignment.data


import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class DefaultThamanyaRepositoryTest : KoinTest {
    @After
    fun tearDown() {
        // ensure a clean Koin between tests
        stopKoin()
    }

    @Test
    fun `mainContent maps remote to domain`() = runTest {
        val json = loadJson("main_content_page1.json")
        val engine = MockEngine { respond(content = json, headers =  jsonHeaders) }
        startKoin { modules(testModule(engine)) }

        KoinTestRule.create { modules(testModule(engine)) }

        val repo by inject<ThamanyaRepository>()
        repo.mainContent().test {
            val domain = awaitItem()
            // Assert meaningful domain fields that your mapper sets.
            // Adjust these to your actual domain models.
            assertThat(domain.sections).isNotEmpty()
            assertThat(domain.pagination?.nextPage).isNotEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `searchContent maps correctly`() = runTest {
        val json = loadJson("search.json")
        val engine = MockEngine { respond(content = json, headers =  jsonHeaders) }

        KoinTestRule.create { modules(testModule(engine)) }
        startKoin { modules(testModule(engine)) }

        val repo by inject<ThamanyaRepository>()
        repo.searchContent(text = "politics").test {
            val result = awaitItem()
            // Assert fields produced by ContentSearchRemote.toDomain()
            assertThat(result.sections).isNotEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `mainContent with pagePath loads page2 and merges in VM later`() = runTest {
        val page2 = loadJson("main_content_page2.json")
        var requested = ""
        val engine = MockEngine { request ->
            requested = request.url.fullPath
            respond(content = page2, headers =  jsonHeaders)
        }

        KoinTestRule.create { modules(testModule(engine)) }
        startKoin { modules(testModule(engine)) }

        val repo by inject<ThamanyaRepository>()
        repo.mainContent(pagePath = "/home_sections?page=2").test {
            awaitItem()
            awaitComplete()
        }
        assertThat(requested).isEqualTo("/home_sections?page=2")
    }
}
