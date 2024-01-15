package com.partyapp_admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.partyapp_admin.screens.DetailsPage
import com.partyapp_admin.screens.UpdateProductDetails
import com.partyapp_admin.viewModels.ProductsViewModel
import com.partyapp_admin.viewModels.UpdateProductViewModel

@Composable

fun DetailsGraph(

    navController: NavHostController = rememberNavController(),
    productsViewModel: ProductsViewModel,
    updateProductViewModel: UpdateProductViewModel
) {


    NavHost(navController = navController, startDestination = DetailsScreen.ProductDisplay.route) {
        composable(route = DetailsScreen.ProductDisplay.route) {
            DetailsPage(
                product = productsViewModel.onClick.value,
                navHostController = navController,
                updateProductViewModel = updateProductViewModel
            )
        }

        composable(route = DetailsScreen.UpdateProduct.route) {
            UpdateProductDetails(
                productsViewModel = productsViewModel,
                updateProductViewModel = updateProductViewModel
            )

        }


    }
}


sealed class DetailsScreen(val route: String) {
    data object ProductDisplay : DetailsScreen(route = "product_whisky")
    data object UpdateProduct : DetailsScreen(route = "update_product")


}