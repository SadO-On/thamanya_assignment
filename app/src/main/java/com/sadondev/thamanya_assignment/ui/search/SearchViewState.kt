package com.sadondev.thamanya_assignment.ui.search

import com.sadondev.thamanya_assignment.domain.models.ContentSearch


sealed class SearchViewState {
    data object Loading : SearchViewState()
    data class Data(val value: ContentSearch) : SearchViewState()
    data class Error(val message: String) : SearchViewState()
}