package com.partyapp_admin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.partyapp_admin.screens.DetailsPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DetailsGraph(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()){

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Details Page") })
        }
    ){
            paddingValues ->
        Card(modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            NavHost(navController = navController, startDestination = DetailsScreen.ProductWhisky.route){
                composable(route = DetailsScreen.ProductWhisky.route){
                    DetailsPage()
                }

        }
    }


    }



}

sealed class DetailsScreen(val route: String) {
    data object ProductWhisky : DetailsScreen(route = "product_whisky")
    data object ProductCarbonated : DetailsScreen(route = "product_carbonated")
    data object SearchScreen : DetailsScreen(route = "search")
}