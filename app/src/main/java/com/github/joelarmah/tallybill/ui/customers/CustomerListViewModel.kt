package com.github.joelarmah.tallybill.ui.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.joelarmah.tallybill.data.CustomerRepository
import com.github.joelarmah.tallybill.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
): ViewModel() {

    private val customers = customerRepository.getCustomers()

    private val numLoadingItems = MutableStateFlow(0)
    val uiState = combine(customers, numLoadingItems) { customers, loadingItems ->
        when (customers) {
            is Resource.Error -> CustomerUiState(errorMessages = "Error")
            is Resource.Loading -> CustomerUiState(isLoading = true)
            is Resource.Success -> CustomerUiState(customers = customers.data, isLoading = loadingItems > 0)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CustomerUiState(isLoading = true)
    )

    fun refresh() {

    }
    fun deleteCustomer(id: String) {

    }

    fun addCustomer() {

    }

}