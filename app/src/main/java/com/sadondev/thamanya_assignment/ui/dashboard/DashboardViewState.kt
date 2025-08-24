package com.sadondev.thamanya_assignment.ui.dashboard

import com.sadondev.thamanya_assignment.ui.models.UiSection

sealed class DashboardViewState {
    data object Loading : DashboardViewState()
    data class Data(val data: List<UiSection>) : DashboardViewState()
    data class Error(private val message: String) : DashboardViewState()
}