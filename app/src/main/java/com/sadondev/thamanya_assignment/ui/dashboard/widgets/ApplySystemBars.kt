package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.material3.MaterialTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.*

@Composable
fun ApplySystemBars(darkTheme: Boolean) {
    val systemUi = rememberSystemUiController()
    val colors = MaterialTheme.colorScheme
    val darkIcons = !darkTheme

    SideEffect {
        systemUi.setStatusBarColor(
            color = colors.background,
            darkIcons = darkIcons
        )
        systemUi.setNavigationBarColor(
            color = colors.background,
            darkIcons = darkIcons,
            navigationBarContrastEnforced = false
        )
    }
}