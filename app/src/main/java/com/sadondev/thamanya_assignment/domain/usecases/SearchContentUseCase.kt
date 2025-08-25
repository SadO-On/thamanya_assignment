package com.sadondev.thamanya_assignment.domain.usecases

import com.sadondev.thamanya_assignment.data.ThamanyaRepository
import com.sadondev.thamanya_assignment.domain.models.ContentSearch
import kotlinx.coroutines.flow.Flow

class SearchContentUseCase(private val repository: ThamanyaRepository) {

    operator fun invoke(text: String? = null): Flow<ContentSearch> {
        return repository.searchContent(text)
    }
}