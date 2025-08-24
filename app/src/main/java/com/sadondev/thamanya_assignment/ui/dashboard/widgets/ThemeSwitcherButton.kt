package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ThemeSwitcherButton(
    isDark: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onToggle, modifier = modifier) {
        Icon(
            imageVector = if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
            contentDescription = if (isDark) "Switch to Light" else "Switch to Dark",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
