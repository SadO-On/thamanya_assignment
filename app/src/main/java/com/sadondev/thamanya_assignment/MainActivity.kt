package com.sadondev.thamanya_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.sadondev.thamanya_assignment.ui.dashboard.widgets.ApplySystemBars
import com.sadondev.thamanya_assignment.ui.theme.ThamanyaAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDark by rememberSaveable { mutableStateOf(true) }

            ThamanyaAssignmentTheme(darkTheme = isDark, dynamicColor = false) {
                Surface {
                    ApplySystemBars(darkTheme = isDark)
                    MainNavGraph(
                        isDark = isDark,
                        onToggle = {
                            isDark = !isDark
                        }
                    )
                }
            }
        }
    }
}



/**
 * Polish list
 * [] Modify correct information for all models
 * [] Fix colors
 * [] Make sure All widgets has Preview
 * [X] Rive Loading
 * [] Remove unnecessary comments
 *
 * */