package com.partyapp_admin.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.partyapp_admin.R
import com.partyapp_admin.data.Product
import com.partyapp_admin.navigation.DetailsScreen
import com.partyapp_admin.viewModels.UpdateProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(
    modifier: Modifier = Modifier, product: Product,
    navHostController: NavHostController,
    updateProductViewModel: UpdateProductViewModel
) {
    val context = LocalContext.current
    var show by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = product.name)

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                modifier = modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = product.name
            )
            Text(
                text = product.name, modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Description", modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = product.desc,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Remaining ${product.amount}",
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium
            )

            if (product.amount < 100) {
                Text(
                    text = "The product amount is low please make an order",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red,
                    fontSize = 11.sp
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = if (product.available) {
                        painterResource(id = R.drawable.baseline_check_circle_outline_24)
                    } else {
                        painterResource(id = R.drawable.baseline_block_24)
                    },
                    contentDescription = product.name,
                    tint = if (product.available) {
                        colorResource(id = R.color.green)
                    } else {
                        Color.Red
                    },
                    modifier = modifier
                        .size(13.dp)
                        .padding(top = 1.dp)
                )

                Text(text = " Available", style = MaterialTheme.typography.bodyMedium)
            }

            Text(
                text = "Amount bought ${product.amountBought}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )


            OutlinedButton(
                onClick = {
                    navHostController.navigate(DetailsScreen.UpdateProduct.route) {
                        launchSingleTop = true
                    }


                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Update Product", color = MaterialTheme.colorScheme.surface)

            }
            OutlinedButton(
                onClick = {
                    show = true

                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Delete Product", color = MaterialTheme.colorScheme.surface)

            }

            if (show) {
                AlertDialog(
                    onDismissRequest = { show = false },
                    confirmButton = {
                        OutlinedButton(onClick = {
                            show = false

                            scope.launch {
                                updateProductViewModel.deleteProduct(product.name)
                            }

                            Toast.makeText(
                                context,
                                updateProductViewModel.deleteStatus,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }) {
                            Text(text = "Confirm", color = MaterialTheme.colorScheme.surfaceTint)
                        }
                    },
                    dismissButton = {
                        OutlinedButton(onClick = {
                            show = false
                        }) {
                            Text(text = "Dismiss", color = MaterialTheme.colorScheme.surfaceTint)
                        }
                    },
                    title = {
                        Text(text = "Delete ${product.name}")
                    },
                    text = {
                        Text(
                            text = "You are About to delete ${product.name}. This action is not reversible." +
                                    " Press Confirm to proceed",
                            color = MaterialTheme.colorScheme.surfaceTint
                        )
                    },
                )
            }

        }

    }


}