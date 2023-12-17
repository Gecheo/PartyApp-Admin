package com.partyapp_admin.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.partyapp_admin.navigation.AuthGraph


@Composable
fun EntryScreen(modifier: Modifier = Modifier,navController: NavHostController){
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome To The Party")
        OutlinedButton(
            onClick = {
            navController.navigate(AuthGraph.LoginPage.route)
            }) {
            Text(text = "Navigate To Login")
        }

    }
}