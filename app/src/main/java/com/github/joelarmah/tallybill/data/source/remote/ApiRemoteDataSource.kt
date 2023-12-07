package com.github.joelarmah.tallybill.data.source.remote

import android.os.SystemClock
import com.github.joelarmah.tallybill.data.Customer
import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

class ApiRemoteDataSource @Inject constructor(): RemoteDatasource {

    private val customerCache = ArrayList<Customer>()
    private var lastDelay = 0L

    override suspend fun getCustomers(): List<Customer> {
        simulateApiDelay()
        return customerCache
    }

    override suspend fun addCustomer(customer: Customer): Customer {
        simulateApiDelay()
        val planetToAdd = if (customer.id == null) customer.copy(id = UUID.randomUUID().toString()) else customer
        customerCache.add(planetToAdd)
        return planetToAdd
    }

//    override suspend fun deleteCustomer(customerId: String) {
//        simulateApiDelay()
//        customerCache.removeIf { it.id == customerId }
//    }

    private suspend fun simulateApiDelay() {
        //(this logic is purely to avoid 3x simulated delays when adding the sample planets)
        if (SystemClock.uptimeMillis() > lastDelay + 500) {
            delay(2000)
            lastDelay = SystemClock.uptimeMillis()
        }
    }
}