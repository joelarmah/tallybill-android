package com.github.joelarmah.tallybill.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

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
