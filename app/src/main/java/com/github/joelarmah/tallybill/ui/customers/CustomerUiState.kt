package com.github.joelarmah.tallybill.ui.customers

import com.github.joelarmah.tallybill.data.Customer

data class CustomerUiState(

    val customers: List<Customer> = emptyList(),
    var isLoading: Boolean = false,
    var errorMessages: String = ""

//    data class NoCustomers(
//        override val isLoading: Boolean,
//        override val errorMessages: List<String>
//    ) : CustomerUiState
//
//    data class HasCustomers(
//        val customers: List<Customer>,
//        val selectedCustomer: Customer,
//        val isArticleOpen: Boolean,
//        val favorites: Set<String>,
//        override val isLoading: Boolean,
//        override val errorMessages: List<String>,
//    ): CustomerUiState

)