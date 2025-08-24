package com.sadondev.thamanya_assignment.data

import com.sadondev.thamanya_assignment.data.remote.ThamanyaAPI
import com.sadondev.thamanya_assignment.data.remote.model.MainContentRemote
import com.sadondev.thamanya_assignment.domain.mapper.toDomain
import com.sadondev.thamanya_assignment.domain.models.MainContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DefaultThamanyaRepository(
    private val api: ThamanyaAPI,
    private val json: Json = Json { ignoreUnknownKeys = true },
) : ThamanyaRepository {
    override fun mainContent(pagePath: String?): Flow<MainContent> {
        return api.getMainContent(pagePath)
            .map { remote: MainContentRemote ->
            remote.toDomain(json)
        }
    }
}