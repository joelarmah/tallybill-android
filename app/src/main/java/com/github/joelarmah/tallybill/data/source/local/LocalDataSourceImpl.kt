package com.github.joelarmah.tallybill.data.source.local

import com.github.joelarmah.tallybill.data.Customer
import com.github.joelarmah.tallybill.data.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImpl internal constructor(
    private val customerDao: CustomerDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): LocalDataSource {

     override fun getCustomers(): Flow<Resource<List<Customer>>> {
        return customerDao.getCustomers().map {
            Resource.Success(it.map { entity -> entity.toCustomer() })
        }
    }

    override fun getCustomerById(customerId: String): Flow<Resource<Customer?>> {
        return customerDao.getCustomerById(customerId).map {
            Resource.Success(it?.toCustomer())
        }
    }

    override suspend fun setCustomers(customers: List<Customer>) {
        customerDao.setCustomers(customers.map { it.toCustomerEntity() })
    }

    override suspend fun deleteCustomer(customerId: String) = withContext<Unit>(ioDispatcher) {
        customerDao.deleteCustomerById(customerId)
    }

    override suspend fun addCustomer(customer: Customer) {
        customerDao.insertCustomer(customer.toCustomerEntity())
    }

}