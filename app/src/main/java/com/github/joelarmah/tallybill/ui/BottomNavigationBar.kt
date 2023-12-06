package com.github.joelarmah.tallybill.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.joelarmah.tallybill.ui.customers.CustomerListScreen
import com.github.joelarmah.tallybill.ui.dashboard.DashboardScreen
import com.github.joelarmah.tallybill.ui.products.ProductListScreen
import com.github.joelarmah.tallybill.ui.settings.SettingsScreen
import com.github.joelarmah.tallybill.util.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEach { navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }
            composable(Screen.Customers.route) {
                CustomerListScreen()
            }
            composable(Screen.Add.route) {
                // AddScreen()
            }
            composable(Screen.Products.route) {
                ProductListScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = "",
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = Screen.Dashboard.label,
                icon = Icons.Outlined.Home,
                route = Screen.Dashboard.route
            ),
            BottomNavigationItem(
                label = Screen.Customers.label,
                icon = Icons.Outlined.Person,
                route = Screen.Customers.route
            ),
            BottomNavigationItem(
                label = "",
                icon = Icons.Outlined.Add,
                route = Screen.Add.route
            ),
            BottomNavigationItem(
                label = Screen.Products.label,
                icon = Icons.Outlined.List,
                route = Screen.Products.route
            ),
            BottomNavigationItem(
                label = Screen.Settings.label,
                icon = Icons.Outlined.Settings,
                route = Screen.Settings.route
            )
        )
    }
}

sealed class Screen(val route: String, val label: String) {
    object Dashboard: Screen(Routes.DASHBOARD, "Dashboard")
    object Products: Screen(Routes.PRODUCTS_LIST, "Products")
    object Add: Screen(Routes.ADD, "Add")
    object Customers: Screen(Routes.CUSTOMERS_LIST, "Customers")
    object Settings: Screen(Routes.SETTINGS, "Settings")
}