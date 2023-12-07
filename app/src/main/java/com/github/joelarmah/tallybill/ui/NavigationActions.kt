package com.github.joelarmah.tallybill.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.joelarmah.tallybill.ui.CustomerDestinationsArgs.CUSTOMER_ID_ARG
import com.github.joelarmah.tallybill.ui.CustomerDestinationsArgs.TITLE_ARG
import com.github.joelarmah.tallybill.ui.CustomerDestinationsArgs.USER_MESSAGE_ARG
import com.github.joelarmah.tallybill.ui.CustomersScreens.ADD_EDIT_CUSTOMER_SCREEN
import com.github.joelarmah.tallybill.ui.CustomersScreens.CUSTOMERS_SCREEN

private object CustomersScreens {
    const val CUSTOMERS_SCREEN = "customers"
    const val ADD_EDIT_CUSTOMER_SCREEN = "addEditCustomer"
}

object CustomerDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val CUSTOMER_ID_ARG = "customerId"
    const val TITLE_ARG = "title"
}

object CustomerDestinations {
    const val CUSTOMERS_ROUTE = "$CUSTOMERS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_EDIT_CUSTOMER_ROUTE = "$ADD_EDIT_CUSTOMER_SCREEN/{$TITLE_ARG}?$CUSTOMER_ID_ARG={$CUSTOMER_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToCustomers(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(
            CUSTOMERS_SCREEN.let {
                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
            }
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToAddEditCustomer(title: String, planetId: String?) {
        navController.navigate(
            "$ADD_EDIT_CUSTOMER_SCREEN/$title".let {
                if (planetId != null) "$it?$CUSTOMER_ID_ARG=$planetId" else it
            }
        )
    }
}