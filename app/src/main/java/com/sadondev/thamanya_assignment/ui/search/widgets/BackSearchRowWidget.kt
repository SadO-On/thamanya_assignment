package com.sadondev.thamanya_assignment.ui.search.widgets


import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme

@Composable
fun BackSearchRowWidget(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    showBorder: Boolean = true
) {
    val focus = LocalFocusManager.current
    val shape = RoundedCornerShape(20.dp)
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.width(8.dp))

        TextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            placeholder = { Text(placeholder) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focus.clearFocus()
                    onSearch(query)
                }
            ),
            shape = shape,
            colors = colors,
            modifier = Modifier
                .weight(1f)
                .clip(shape)
                .then(
                    if (showBorder)
                        Modifier.border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            shape
                        )
                    else Modifier
                )
        )
    }
}

@Preview(name = "BackSearchRow – Light", apiLevel = 35, showBackground = true)
@Composable
private fun BackSearchRowLightPreview() {
    ThamanyaAssignmentTheme(darkTheme = false, dynamicColor = false) {
        var q by remember { mutableStateOf("Offers") }
        Surface {
            BackSearchRowWidget(
                query = q,
                onQueryChange = { q = it },
                onBackClick = {},
                onSearch = {}
            )
        }
    }
}

@Preview(
    name = "BackSearchRow – Dark",
    apiLevel = 35,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun BackSearchRowDarkPreview() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        var q by remember { mutableStateOf("") }
        Surface {
            BackSearchRowWidget(
                query = q,
                onQueryChange = { q = it },
                onBackClick = {},
                onSearch = {}
            )
        }
    }
}
