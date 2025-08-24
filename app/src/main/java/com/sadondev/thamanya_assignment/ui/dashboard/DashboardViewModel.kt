package com.sadondev.thamanya_assignment.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadondev.thamanya_assignment.domain.usecases.GetMainContentUseCase
import com.sadondev.thamanya_assignment.ui.dashboard.merge.mergeWithCount
import com.sadondev.thamanya_assignment.ui.mapper.toUiSections
import com.sadondev.thamanya_assignment.ui.models.UiSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val useCase: GetMainContentUseCase, // returns Flow<MainContent> of a single page
) : ViewModel() {
    private val _uiState = MutableStateFlow<DashboardViewState>(DashboardViewState.Loading)
    val uiState: StateFlow<DashboardViewState> = _uiState.asStateFlow()

    private var accumulated: List<UiSection> = emptyList()
    private var nextPage: String? = null
    private var loadingMore = false

    init { loadFirstPage() }

    private fun loadFirstPage() {
        _uiState.value = DashboardViewState.Loading
        viewModelScope.launch {
            useCase()
                .map { main ->
                    nextPage = main.pagination?.nextPage
                    main.sections.toUiSections()
                }
                .catch { _uiState.value = DashboardViewState.Error(it.message ?: "Unknown error") }
                .collect { uiSections ->
                    accumulated = uiSections
                    _uiState.value = DashboardViewState.Data(
                        sections = accumulated,
                        nextPage = nextPage,
                        isLoadingMore = false
                    )
                }
        }
    }

    fun loadNextPage() {
        val currentNext = nextPage ?: return
        if (loadingMore) return
        loadingMore = true

        // show footer spinner
        (_uiState.value as? DashboardViewState.Data)?.let {
            _uiState.value = it.copy(isLoadingMore = true)
        }

        viewModelScope.launch {
            try {
                // fetch exactly one page
                val main = useCase(pagePath = currentNext).first()

                val newNext = main.pagination?.nextPage   // may be null (end)
                val newUiSections = main.sections.toUiSections()

                val (merged, addedCount) = accumulated.mergeWithCount(newUiSections)

                accumulated = merged
                nextPage = newNext
                loadingMore = false

                _uiState.value = DashboardViewState.Data(
                    sections = accumulated,
                    nextPage = nextPage,
                    isLoadingMore = false
                )

                // Optional debug log
                android.util.Log.d(
                    "Paging",
                    "Loaded page: key=$currentNext -> next=$newNext, added=$addedCount"
                )

                // If page had nothing for this section but there's another page,
                // do NOT auto-loop here. The grid trigger will fire again when needed.
                // (Your SquareGrid has the lastRequestedKey guard to prevent duplicates.)

            } catch (e: Exception) {
                loadingMore = false
                (_uiState.value as? DashboardViewState.Data)?.let {
                    _uiState.value = it.copy(isLoadingMore = false)
                } ?: run {
                    _uiState.value = DashboardViewState.Error(e.message ?: "Load more failed")
                }
            }
        }
    }}



//fun loadNextPage() { val currentNext = nextPage ?: return if (loadingMore) return loadingMore = true // Show spinner (uiState.value as? DashboardViewState.Data)?.let { _uiState.value = it.copy(isLoadingMore = true) } viewModelScope.launch { useCase(pagePath = currentNext) .map { main -> nextPage = main.pagination?.nextPage main.sections.toUiSections() } .catch { e -> loadingMore = false // hide spinner but keep existing data (_uiState.value as? DashboardViewState.Data)?.let { _uiState.value = it.copy(isLoadingMore = false) } ?: run { _uiState.value = DashboardViewState.Error(e.message ?: "Load more failed") } } .collect { newPageSections -> loadingMore = false accumulated = accumulated.mergeWith(newPageSections) _uiState.value = DashboardViewState.Data( sections = accumulated, nextPage = nextPage, isLoadingMore = false ) } } }