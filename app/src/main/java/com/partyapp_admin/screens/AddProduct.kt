//package com.partyapp_admin.screens
//
//
//import android.annotation.SuppressLint
//import android.net.Uri
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Card
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Switch
//import androidx.compose.material3.SwitchDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberAsyncImagePainter
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("NewApi")
//@Composable
//fun AdminAddImage(
//    modifier: Modifier = Modifier,
//    uploadImageToStorageViewModel: UploadImageToStorageViewModel
//) {
//
//    val uploadState = uploadImageToStorageViewModel.uploadTask.collectAsState()
//    val uploadTextState = uploadImageToStorageViewModel.uploadTextStatus.collectAsState()
//    val context = LocalContext.current
//
//    var selectedImages by remember {
//        mutableStateOf<List<Uri>>(emptyList())
//    }
//    var description by remember {
//        mutableStateOf("")
//    }
//    var image: Uri = Uri.EMPTY
//
//    var name by remember {
//        mutableStateOf("")
//    }
//    var liters by remember {
//        mutableStateOf("")
//    }
//    var price by remember {
//        mutableStateOf("")
//    }
//    var amount by remember {
//        mutableStateOf("")
//    }
//    var isAvailable by remember {
//        mutableStateOf(false)
//    }
//
//    var isClicked by remember {
//        mutableStateOf(false)
//    }
//
//
//    // for the dropdown menu
//    val categories = arrayOf("Whisky", "Carbonated", "Water", "Energy Drinks")
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    var category by remember {
//        mutableStateOf(categories[0])
//    }
//
//
//    val pickMedia =
//        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
//            selectedImages = it
//
//        }
//    Card(
//        modifier = modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState()),
//    ) {
//        Text(
//            text = "Admin Add Products",
//            fontStyle = FontStyle.Normal,
//            fontWeight = FontWeight.Bold,
//            modifier = modifier.fillMaxWidth(),
//            style = MaterialTheme.typography.headlineLarge,
//            textAlign = TextAlign.Center
//        )
//
//        OutlinedTextUpload(
//            value = description,
//            onValueChanged = { description = it },
//            label = "Description"
//        )
//        OutlinedTextUpload(value = name, onValueChanged = { name = it }, label = "Product name")
//        OutlinedTextUpload(value = price, onValueChanged = { price = it }, label = "Price")
//        OutlinedTextUpload(value = liters, onValueChanged = { liters = it }, label = "Size")
//        OutlinedTextUpload(value = amount, onValueChanged = { amount = it }, label = "Total amount")
//
//        Box(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(3.dp)
//
//        ) {
//            ExposedDropdownMenuBox(
//                expanded = expanded, onExpandedChange = { expanded = !expanded }) {
//                OutlinedTextField(
//                    value = category,
//                    onValueChange = {},
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
//                        unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
//                        cursorColor = MaterialTheme.colorScheme.surfaceTint,
//                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
//                        focusedLabelColor = MaterialTheme.colorScheme.surfaceTint,
//                        unfocusedLabelColor = MaterialTheme.colorScheme.surface,
//                    ),
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                    modifier = modifier
//                        .menuAnchor()
//                        .fillMaxWidth()
//                        .padding(10.dp)
//                )
//                ExposedDropdownMenu(
//                    expanded = expanded,
//                    modifier = modifier.background(
//                        color = MaterialTheme.colorScheme.secondary,
//                        shape = RoundedCornerShape(10)
//                    ),
//                    onDismissRequest = { expanded = false }) {
//
//                    categories.forEach { item ->
//                        DropdownMenuItem(
//                            text = { Text(text = item) },
//                            onClick = {
//                                category = item
//                                expanded = false
//                            })
//                    }
//
//                }
//
//            }
//        }
//
//
//
//        Row(
//            modifier = modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Is available", color = MaterialTheme.colorScheme.surfaceTint,
//                modifier = modifier.padding(10.dp)
//            )
//            Switch(
//                checked = isAvailable,
//                onCheckedChange = { isAvailable = !isAvailable },
//                colors = SwitchDefaults.colors(
//                    checkedBorderColor = MaterialTheme.colorScheme.secondary,
//                    checkedIconColor = MaterialTheme.colorScheme.secondary,
//                    uncheckedBorderColor = MaterialTheme.colorScheme.onSecondary,
//                    uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary
//                )
//            )
//        }
//
//        OutlinedButton(
//            onClick = {
//                pickMedia.launch("image/*")
//            },
//            modifier = modifier
//                .padding(10.dp)
//                .fillMaxWidth()
//        ) {
//            Text(text = "Select Image", color = MaterialTheme.colorScheme.surfaceTint)
//
//        }
////        OutlinedButton(
//////            onClick = {
//////                val product = UploadProduct(
//////                    isAvailable = isAvailable,
//////                    desc = description,
//////                    image = null,
//////                    name = name,
//////                    liters = liters,
//////                    price = price,
//////                    amount = amount.toInt(),
//////                    category = category
//////
//////                )
//////                uploadImageToStorageViewModel.uploadImage("pngAlcohol", image, product)
//////
//////                Toast.makeText(context, uploadState.value, Toast.LENGTH_SHORT).show()
//////                Toast.makeText(context, uploadTextState.value, Toast.LENGTH_SHORT).show()
////            },
////            modifier = modifier
////                .padding(10.dp)
////                .fillMaxWidth()
////        ) {
////            Text(text = "Upload Product", color = MaterialTheme.colorScheme.surfaceTint)
////
////        }
//
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = modifier.height(400.dp)
//        ) {
//            items(selectedImages) { uri ->
//                Image(
//                    painter = rememberAsyncImagePainter(uri),
//                    contentDescription = null,
//                    modifier = modifier
//                        .padding(1.dp)
//                        .size(100.dp)
//                        .clickable(
//                            enabled = true,
//                            onClickLabel = "Selected",
//                            onClick = {
//                                image = uri
//                                isClicked = !isClicked
//                            }
//                        ),
//                    alpha = if (isClicked) {
//                        0.5f
//                    } else {
//                        0.8f
//                    },
//                    contentScale = ContentScale.Crop
//
//
//                )
//            }
//        }
//
//
//    }
//
//
//}
//
//@Composable
//fun OutlinedTextUpload(
//    modifier: Modifier = Modifier,
//    value: String, onValueChanged: (String) -> Unit,
//    label: String
//) {
//
//    OutlinedTextField(
//        value = value,
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        onValueChange = onValueChanged,
//        placeholder = {
//            Text(text = label, color = MaterialTheme.colorScheme.onSecondary)
//        },
//        label = {
//            Text(text = label, color = MaterialTheme.colorScheme.onSecondary)
//        },
//        colors = OutlinedTextFieldDefaults.colors(
//            focusedBorderColor = MaterialTheme.colorScheme.surfaceTint,
//            unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
//            cursorColor = MaterialTheme.colorScheme.surfaceTint,
//            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
//            focusedLabelColor = MaterialTheme.colorScheme.surfaceTint,
//            unfocusedLabelColor = MaterialTheme.colorScheme.surface,
//        ),
//        shape = RoundedCornerShape(50)
//
//    )
//
//}
//
//
////@Composable
////@Preview(showBackground = true, showSystemUi = true)
////fun Show(){
////    OutlinedTextUpload()
////}