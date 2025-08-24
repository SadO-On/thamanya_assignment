package com.sadondev.thamanya_assignment

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sadondev.thamanya_assignment.ui.dashboard.DashboardScreen
import com.sadondev.thamanya_assignment.ui.search.SearchScreen


@Composable
fun MainNavGraph(
    isDark: Boolean,
    onToggle: () -> Unit
) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DASHBOARD_ROUTE) {
        composable(route = DASHBOARD_ROUTE) {
            DashboardScreen(
                isDark = isDark,
                onToggle = onToggle,
                onSearchClick = {
                    navController.navigate(SEARCH_ROUTE)
                })
        }
        composable(route = SEARCH_ROUTE) {
            SearchScreen {
                navController.navigateUp()
            }
        }
    }

}


const val DASHBOARD_ROUTE = "dashboard"
const val SEARCH_ROUTE = "search"