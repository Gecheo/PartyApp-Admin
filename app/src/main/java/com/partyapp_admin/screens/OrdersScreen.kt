package com.partyapp_admin.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.partyapp_admin.R
import com.partyapp_admin.data.FinalCustomerOrder
import com.partyapp_admin.navigation.BottomBar
import com.partyapp_admin.navigation.HomeBottomBar
import com.partyapp_admin.viewModels.OrdersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    ordersViewModel: OrdersViewModel
) {
    val orders = ordersViewModel.orders.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Orders") },

            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            actions = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_addchart_24),
                    contentDescription = stringResource(id = R.string.addProduct_icon),
                    modifier = modifier
                        .clickable {
                            navController.navigate(HomeBottomBar.AddProduct.route) {

                            }

                        }
                        .padding(start = 10.dp, end = 10.dp))

                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = stringResource(id = R.string.search_icon),
                    modifier = modifier
                        .clickable {
                            navController.navigate(HomeBottomBar.SearchProduct.route)

                        }
                        .padding(start = 10.dp, end = 10.dp))


            }
        )
    },

        bottomBar = {
            BottomBar(navController = navController)
        }) { paddingValues ->
        if (orders.value.isNotEmpty()) {
            Card(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    items(orders.value) { item ->
                        OrderItem(order = item, ordersViewModel = ordersViewModel)
                    }

                }


            }

        } else {
            Card(modifier = modifier.fillMaxSize()) {
                Text(
                    text = "Order Something",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

            }
        }

    }

}

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    ordersViewModel: OrdersViewModel,
    order: FinalCustomerOrder
) {
    var approved by remember {
        mutableStateOf(false)
    }
    var delivered by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier.padding(start = 5.dp, end = 5.dp, top = 1.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(modifier = modifier.fillMaxWidth()) {

            Column(modifier = modifier.width(80.dp)) {
                Text(
                    text = "Customer",
                    modifier

                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Text(
                    text = " ${ordersViewModel.user}",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )

            }
            Column(modifier = modifier.padding(2.dp)) {
                Text(
                    text = "Product",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${order.productTitle} (${order.orderQuantity})",
                    modifier

                        .padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )

            }
            Column(modifier = modifier.padding(2.dp)) {
                Text(
                    text = "Amount",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Text(
                    text =" ${ order.orderPrice }0",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )

            }
            Column(modifier = modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Approved",
                    modifier

                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = if (!approved) {
                        painterResource(id = R.drawable.baseline_block_24)
                    } else {
                        painterResource(id = R.drawable.baseline_check_circle_outline_24)
                    },
                    contentDescription = "Approved",
                    tint = if (approved) {
                        colorResource(id = R.color.green)
                    } else {
                        Color.Red
                    },
                    modifier = modifier
                        .padding(top = 3.dp)
                        .clickable {
                            approved = !approved
                        }

                )

            }
            Column(modifier = modifier.padding(2.dp)) {
                Text(
                    text = "Location",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Nyamira",
                    modifier
                        .padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                )

            }

            Column {
                Text(
                    text = "Delivered",
                    modifier

                        .padding(2.dp),
                    style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = if (!delivered) {
                        painterResource(id = R.drawable.baseline_block_24)
                    } else {
                        painterResource(id = R.drawable.baseline_check_circle_outline_24)
                    },
                    contentDescription = "delivered icon",
                    tint = if (delivered) {
                        colorResource(id = R.color.green)
                    } else {
                        Color.Red
                    },
                    modifier = modifier
                        .padding(top = 3.dp)
                        .clickable {
                            delivered = !delivered
                        }

                )

            }


        }
        Row {

            Text(
                text = "Order Time : ",
                modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
            )
            Text(
                text = order.orderTimestamp,
                modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace
            )


        }
        Row {

            Text(
                text = "Date of delivery",
                modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold
            )
            Text(
                text = order.deliveryDateOne,
                modifier
                    .padding(2.dp),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Monospace
            )


        }
    }
}

