package com.sadondev.thamanya_assignment.ui.dashboard

import com.sadondev.thamanya_assignment.domain.models.MainContent

sealed class DashboardViewState {
    data object Loading : DashboardViewState()
    data class Data(private val data: MainContent) : DashboardViewState()
    data class Error(private val message: String) : DashboardViewState()
}