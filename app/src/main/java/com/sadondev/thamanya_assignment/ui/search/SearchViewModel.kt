package com.sadondev.thamanya_assignment.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadondev.thamanya_assignment.domain.usecases.SearchContentUseCase
import com.sadondev.thamanya_assignment.ui.mapper.toUiSections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class SearchViewModel(
    private val useCase: SearchContentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val uiState: StateFlow<SearchViewState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    init {
        viewModelScope.launch {
            _query
                .debounce(200)
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .flatMapLatest { q ->
                    useCase(q)
                        .onStart {
                            _uiState.value = SearchViewState.Loading
                        }
                        .map { main -> main }

                }
                .catch { e -> SearchViewState.Error(e.message ?: "Unknown error") }
                .collect { value ->
                    Log.i("SearchViewState.Data", value.toString())
                    _uiState.value = SearchViewState.Data(value) }
        }
    }

    fun onQueryChange(new: String) {
        _query.value = new
    }

}