package com.sadondev.thamanya_assignment.domain.usecases

import com.sadondev.thamanya_assignment.data.ThamanyaRepository
import com.sadondev.thamanya_assignment.domain.models.MainContent
import kotlinx.coroutines.flow.Flow

class GetMainContentUseCase(private val repository: ThamanyaRepository) {

    operator fun invoke(pagePath: String? = null): Flow<MainContent> {
        return repository.mainContent(pagePath)
    }
}