package com.github.joelarmah.tallybill.ui.customers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.joelarmah.tallybill.R
import com.github.joelarmah.tallybill.data.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerListScreen(
    addCustomer: () -> Unit,
    editCustomer: (String) -> Unit,
//    scaffoldState: ScaffoldState = rememberScaffoldState(),
    customerViewModel: CustomerListViewModel = hiltViewModel()
) {

    val uiState by customerViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
//        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = addCustomer) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.add))
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = customerViewModel::refresh) {
                    Text("Refresh")
                }
                Button(onClick = customerViewModel::addCustomer) {
                    Text("Add sample planets")
                }
            }

            if (uiState.customers.isEmpty()) {
                NoCustomers()
            }
            else {
                CustomerList(
                    editPlanet = editCustomer,
                    deletePlanet = customerViewModel::deleteCustomer,
                    customers = uiState.customers
                )
            }
        }

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Center))
            }
        }
    }

}

@Composable
fun NoCustomers() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        Text(stringResource(R.string.no_customer), color = Color.Gray)
    }
}

@Composable
fun CustomerList(
    editPlanet: (String) -> Unit,
    deletePlanet: (String) -> Unit,
    customers: List<Customer>
) {
    LazyColumn {
        items(customers) {
            CustomerItem(
                customer = it,
                onEdit = editPlanet,
                onDelete = deletePlanet
            )
        }
    }
}

@Composable
fun CustomerItem(
    customer: Customer,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { customer.id?.let { onEdit(it) } }
        .shadow(5.dp, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = customer.name,
                style = MaterialTheme.typography.headlineLarge,
            )

            Text("Phone Number: ${customer.phoneNumber}")
        }

        IconButton(
            modifier = Modifier
                .align(CenterVertically)
                .width(32.dp)
                .height(32.dp),
            onClick = { customer.id?.let { onDelete(it) } }
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Preview
@Composable
fun CustomerPreview() {
    CustomerItem(
        Customer("3242134323", "Joel", "233244245902", "Date"),
        { }) { }
}