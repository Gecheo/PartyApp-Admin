package com.partyapp_admin.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.partyapp_admin.navigation.BottomBar
import com.partyapp_admin.navigation.HomeBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPage(modifier: Modifier = Modifier,navController:NavHostController){
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Admin") })
    },
        bottomBar = {
            BottomBar(navController = navController)
        }) { paddingValues ->
        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "This is the Homepage")
            OutlinedButton(onClick = {
                navController.navigate(HomeBottomBar.Details.route)
            }) {

            }

        }
    }

}