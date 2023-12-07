package com.github.joelarmah.tallybill.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.joelarmah.tallybill.ui.PlanetsDestinationsArgs.PLANET_ID_ARG
import com.github.joelarmah.tallybill.ui.PlanetsDestinationsArgs.TITLE_ARG
import com.github.joelarmah.tallybill.ui.PlanetsDestinationsArgs.USER_MESSAGE_ARG
import com.github.joelarmah.tallybill.ui.CustomersScreens.ADD_EDIT_CUSTOMER_SCREEN
import com.github.joelarmah.tallybill.ui.CustomersScreens.CUSTOMERS_SCREEN

private object CustomersScreens {
    const val CUSTOMERS_SCREEN = "customers"
    const val ADD_EDIT_CUSTOMER_SCREEN = "addEditCustomer"
}

object PlanetsDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val PLANET_ID_ARG = "planetId"
    const val TITLE_ARG = "title"
}

object PlanetsDestinations {
    const val PLANETS_ROUTE = "$CUSTOMERS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
    const val ADD_EDIT_PLANET_ROUTE = "$ADD_EDIT_CUSTOMER_SCREEN/{$TITLE_ARG}?$PLANET_ID_ARG={$PLANET_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToPlanets(userMessage: Int = 0) {
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

    fun navigateToAddEditPlanet(title: String, planetId: String?) {
        navController.navigate(
            "$ADD_EDIT_CUSTOMER_SCREEN/$title".let {
                if (planetId != null) "$it?$PLANET_ID_ARG=$planetId" else it
            }
        )
    }
}