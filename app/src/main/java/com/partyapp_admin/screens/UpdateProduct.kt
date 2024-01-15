package com.partyapp_admin.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.partyapp_admin.data.Product
import com.partyapp_admin.viewModels.ProductsViewModel
import com.partyapp_admin.viewModels.UpdateProductViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductDetails(
    modifier: Modifier = Modifier,
    productsViewModel: ProductsViewModel,
    updateProductViewModel: UpdateProductViewModel

) {
    val context = LocalContext.current
    val product = productsViewModel.onClick.value
    var isAvailable by remember {
        mutableStateOf(true)
    }
    var desc by remember {
        mutableStateOf("")
    }
    var productName by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val updateStatus = updateProductViewModel.updateStatus

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Update ${product.name} details") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Card(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = product.name,
                modifier = modifier
                    .size(200.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),


                )
            OutlinedTextField(
                value = productName,
                onValueChange = {productName = it},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                label = { Text(text = "product Name") },
                placeholder = {
                    Text(text = product.name, color = MaterialTheme.colorScheme.onSecondary)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                    cursorColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
                    focusedLabelColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedLabelColor = MaterialTheme.colorScheme.surface,
                )

            )
            OutlinedTextField(
                value = desc,
                onValueChange = {desc = it},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                label = { Text(text = "Description") },
                placeholder = {
                    Text(text = product.desc, color = MaterialTheme.colorScheme.onSecondary)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                    cursorColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
                    focusedLabelColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedLabelColor = MaterialTheme.colorScheme.surface,
                ),
                minLines = 4,
                maxLines = 8
            )

            OutlinedTextField(
                value = amount,
                onValueChange = {amount = it},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                label = { Text(text = "Amount") },
                placeholder = {
                    Text(text = "Amount", color = MaterialTheme.colorScheme.onSecondary)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                    cursorColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
                    focusedLabelColor = MaterialTheme.colorScheme.surfaceTint,
                    unfocusedLabelColor = MaterialTheme.colorScheme.surface,
                )

            )



            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Is available", color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = modifier.padding(10.dp)
                )
                Switch(
                    checked = isAvailable,
                    onCheckedChange = { isAvailable = !isAvailable },
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = MaterialTheme.colorScheme.secondary,
                        checkedIconColor = MaterialTheme.colorScheme.secondary,
                        uncheckedBorderColor = MaterialTheme.colorScheme.onSecondary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }

            Spacer(modifier = modifier.height(30.dp))

            OutlinedButton(
                onClick = {

                    val updateItem = Product(
                        name = productName,
                        desc = desc,
                        amount = amount.toInt(),
                        available = isAvailable
                    )

                    scope.launch {
                        updateProductViewModel.updateProduct(name = product.name, updateItem )
                        Toast.makeText(context,updateStatus,Toast.LENGTH_LONG).show()
                    }


                          },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                ) {
                Text(text = "Update", color = MaterialTheme.colorScheme.secondary)
                
            }


        }

    }

}

//@Preview
//@Composable
//fun ProductView() {
//    UpdateProductDetails(product = null)
//}