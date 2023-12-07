package com.github.joelarmah.tallybill.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.joelarmah.tallybill.ui.customers.CustomerListScreen
import com.github.joelarmah.tallybill.ui.dashboard.DashboardScreen
import com.github.joelarmah.tallybill.ui.products.ProductListScreen
import com.github.joelarmah.tallybill.ui.settings.SettingsScreen
@Composable
fun NavGraph(
    navController: NavHostController,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
    innerPadding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(Screen.Customers.route) {
            CustomerListScreen(
                addCustomer = { navActions.navigateToAddEditCustomer("Add customer", null) },
                editCustomer = { customerId -> navActions.navigateToAddEditCustomer("Edit Customer", customerId)}
            )
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