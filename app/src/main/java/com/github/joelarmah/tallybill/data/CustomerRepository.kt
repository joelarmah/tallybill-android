package com.github.joelarmah.tallybill.data

import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCustomers(): Flow<Resource<List<Customer>>>
    fun addCustomer(customer: Customer)
}

