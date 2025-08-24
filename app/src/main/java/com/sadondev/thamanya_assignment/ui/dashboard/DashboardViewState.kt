package com.sadondev.thamanya_assignment.ui.dashboard

import com.sadondev.thamanya_assignment.ui.models.UiSection

sealed class DashboardViewState {
    data object Loading : DashboardViewState()
    data class Data(
        val sections: List<UiSection>,
        val nextPage: String?,
        val isLoadingMore: Boolean = false
    ) : DashboardViewState()    data class Error(private val message: String) : DashboardViewState()
}