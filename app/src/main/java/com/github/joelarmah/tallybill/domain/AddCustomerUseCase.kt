package com.github.joelarmah.tallybill.domain

import com.github.joelarmah.tallybill.data.Customer
import com.github.joelarmah.tallybill.data.CustomerRepository
import javax.inject.Inject

class AddCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(customer: Customer) {
        if (customer.name.isEmpty()) {
            throw Exception("Please provide a name")
        }
        customerRepository.addCustomer(customer)
    }

}