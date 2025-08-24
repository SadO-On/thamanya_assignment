package com.sadondev.thamanya_assignment.ui.dashboard.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadondev.thamanya_assignment.ui.theme.Neutral98
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme

@Composable
fun TimeChip(time: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(50) // pill shape
            )
            .padding(horizontal = 8.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            color = Neutral98,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview()
@Composable
fun TimeChipPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        TimeChip("2H 50m")
        TimeChip("45m")
        TimeChip("1H 10m")
    }
}

@Preview(
    apiLevel = 35,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun TimeChipDarkPreview() {
    ThamanyaAssignmentTheme(darkTheme = true, dynamicColor = false) {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                TimeChip("2H 50m")
                TimeChip("45m")
                TimeChip("1H 10m")
            }
        }
    }
}

