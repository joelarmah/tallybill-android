package com.github.joelarmah.tallybill.data.source.remote

import com.github.joelarmah.tallybill.data.Customer

interface RemoteDatasource {
    suspend fun getCustomers(): List<Customer>
    suspend fun addCustomer(customer: Customer): Customer
}