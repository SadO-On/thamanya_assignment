package com.sadondev.thamanya_assignment.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadondev.thamanya_assignment.domain.usecases.GetMainContentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val useCase: GetMainContentUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DashboardViewState?>(null)
    val uiState: StateFlow<DashboardViewState?> = _uiState.asStateFlow()

    init {
        getMainContent()
    }

    fun getMainContent() {
        _uiState.value = DashboardViewState.Loading
        viewModelScope.launch {
            useCase()
                .catch {
                    _uiState.value = DashboardViewState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = DashboardViewState.Data(it)
                }
        }
    }

}