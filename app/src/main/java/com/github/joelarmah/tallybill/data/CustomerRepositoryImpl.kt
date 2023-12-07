package com.github.joelarmah.tallybill.data

import com.github.joelarmah.tallybill.data.source.local.LocalDataSource
import com.github.joelarmah.tallybill.data.source.remote.RemoteDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDatasource,
    private val ioCoroutineDispatcher: CoroutineDispatcher
): CustomerRepository {
    override fun getCustomers(): Flow<Resource<List<Customer>>> {
        return localDataSource.getCustomers()
    }

    override fun addCustomer(customer: Customer) {
        TODO("Not yet implemented")
    }

}