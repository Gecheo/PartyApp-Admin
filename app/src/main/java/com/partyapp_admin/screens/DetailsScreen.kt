package com.partyapp_admin.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(modifier: Modifier = Modifier){




        Card(modifier = modifier
            .fillMaxSize()
            ) {
           Text(text = "Hello Msee")
        }


}