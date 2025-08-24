package com.sadondev.thamanya_assignment.domain.usecases

import com.sadondev.thamanya_assignment.data.ThamanyaRepository
import com.sadondev.thamanya_assignment.domain.models.MainContent
import kotlinx.coroutines.flow.Flow

class SearchContentUseCase(private val repository: ThamanyaRepository) {

    operator fun invoke(text: String? = null): Flow<MainContent> {
        return repository.searchContent(text)
    }
}