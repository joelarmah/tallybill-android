package com.github.joelarmah.tallybill.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun getCustomers() : Flow<List<CustomerEntity>>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customer: CustomerEntity)

    @Query("SELECT * FROM customers WHERE customerId = :customerId")
    fun getCustomerById(customerId: String): Flow<CustomerEntity?>

    /**
     * Update a customer.
     *
     * @param customer planet to be updated
     * @return the number of planets updated. This should always be 1.
     */
    @Update
    suspend fun updatePlanet(customer: CustomerEntity): Int

    /**
     * Delete a customer by id.
     *
     * @return the number of customers deleted.
     */
    @Query("DELETE FROM customers WHERE customerId = :customerId")
    suspend fun deleteCustomerById(customerId: String): Int

    /**
     * Delete all customers.
     */
    @Query("DELETE FROM customers")
    suspend fun deleteAllCustomers()

    @Transaction
    suspend fun setCustomers(customers: List<CustomerEntity>) {
        deleteAllCustomers()
        customers.forEach { insertCustomer(it) }
    }

}
