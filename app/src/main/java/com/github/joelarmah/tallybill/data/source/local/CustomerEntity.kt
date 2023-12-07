package com.github.joelarmah.tallybill.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.joelarmah.tallybill.data.Customer
import java.util.Date
import java.util.UUID

@Entity(tableName = "customers")
data class CustomerEntity(
     @PrimaryKey
    var customerId: String,
    var name: String = "",
    var phoneNumber: String = "",
    var createdAt: String = Date().toString()
) {
    fun toCustomer(): Customer {
        return Customer(
            id = customerId,
            name = name,
            phoneNumber = phoneNumber,
            createdAt = createdAt
        )
    }
}

fun Customer.toCustomerEntity(): CustomerEntity {
    return CustomerEntity(
        customerId = id ?: UUID.randomUUID().toString(),
        name = name,
        phoneNumber = phoneNumber
    )
}