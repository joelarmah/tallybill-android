package com.github.joelarmah.tallybill.ui

import com.github.joelarmah.tallybill.util.Routes

sealed class Screen(val route: String, val label: String) {

    object Dashboard: Screen(Routes.DASHBOARD, "Dashboard")
    object Products: Screen(Routes.PRODUCTS_LIST, "Products")
    object Add: Screen(Routes.ADD, "Add")
    object Customers: Screen(Routes.CUSTOMERS_LIST, "Customers")
    object Settings: Screen(Routes.SETTINGS, "Settings")

}