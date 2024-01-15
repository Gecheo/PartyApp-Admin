package com.partyapp_admin.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.partyapp_admin.R
import com.partyapp_admin.data.Product
import com.partyapp_admin.navigation.BottomBar
import com.partyapp_admin.navigation.HomeBottomBar
import com.partyapp_admin.viewModels.ProductsViewModel
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    productsViewModel: ProductsViewModel
) {

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val whisky = productsViewModel.whisky.collectAsState()
    val context = LocalContext.current
    val carbonated = productsViewModel.carbonated.collectAsState()
    val isCarbonatedLoading = productsViewModel.carbonatedLoading
    val isWhiskyLoading = productsViewModel.whiskyLoading

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Admin Home") },
            colors = TopAppBarDefaults.topAppBarColors(
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
        Card(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Text(
                text = when (currentHour) {
                    in 6..11 -> {
                        "Good morning Admin,"
                    }

                    in 12..16 -> {
                        "Good afternoon Admin,"
                    }

                    in 17..20 -> {
                        "Good Evening Admin,"
                    }

                    in 21..23 -> {
                        "Night Admin,"
                    }

                    else -> {
                        "Good morning admin,"
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Whiskey Products",
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            if (isWhiskyLoading.value) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = modifier
                        .size(40.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    trackColor = MaterialTheme.colorScheme.surface
                )

            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth()
            ) {
                items(whisky.value) { item ->
                    ProductItem(
                        onClick = {
                            productsViewModel.displayProduct(item)
                            navController.navigate(HomeBottomBar.Details.route)
                        }, product = item,
                        context = context
                    )
                }


            }
            Text(
                text = "Carbonated Products",
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            if (isCarbonatedLoading.value) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = modifier
                        .size(40.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    trackColor = MaterialTheme.colorScheme.surface

                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth()
            ) {
                items(carbonated.value) { item ->
                    ProductItem(
                        onClick = {
                            productsViewModel.displayProduct(item)
                            navController.navigate(HomeBottomBar.Details.route){

                                launchSingleTop =true

                            }
                        },
                        product = item,
                        context = context
                    )
                }


            }
        }


    }


}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    onClick: (product: Product) -> Unit,
    product: Product,
    context: Context
) {
    Card (modifier = modifier
        .fillMaxWidth()
        .padding(1.dp)
        .clickable(
            enabled = true
        ) {
            onClick(product)
        }
        .background(color = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp,
            pressedElevation = 7.dp
        )){

        AsyncImage(
            model = ImageRequest.Builder(context = context)
                .data(product.image)
                .crossfade(true)
                .build(),
            modifier = modifier.size(120.dp),
            contentDescription = product.name
        )


        Text(
            text = product.name,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
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
                    .size(12.dp)
                    .padding(top = 3.dp)
            )

            Text(text = "Available",style = MaterialTheme.typography.bodyMedium)
        }


    }

}

//@Composable
//@Preview
//fun ShowItem() {
//    ProductItem()
//}