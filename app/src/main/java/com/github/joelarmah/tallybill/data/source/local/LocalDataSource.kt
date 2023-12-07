package com.github.joelarmah.tallybill.data.source.local

import com.github.joelarmah.tallybill.data.Customer
import com.github.joelarmah.tallybill.data.Resource
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getCustomers(): Flow<Resource<List<Customer>>>
    fun getCustomerById(customerId: String): Flow<Resource<Customer?>>
    suspend fun setCustomers(customers: List<Customer>)
    suspend fun addCustomer(customer: Customer)
    suspend fun deleteCustomer(customerId: String)

}