package com.sadondev.thamanya_assignment.ui.search

import com.sadondev.thamanya_assignment.ui.models.UiSection

sealed class SearchViewState {
    data object Loading : SearchViewState()
    data class Data(val sections: List<UiSection>) : SearchViewState()
    data class Error(val message: String) : SearchViewState()
}