package com.partyapp_admin.screens


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.partyapp_admin.R
import com.partyapp_admin.data.Product
import com.partyapp_admin.navigation.HomeBottomBar
import com.partyapp_admin.viewModels.ProductsViewModel
import com.partyapp_admin.viewModels.SearchScreenViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel,
    productsViewModel: ProductsViewModel
) {

    var searchTerm by remember {
        mutableStateOf("")
    }

    val searchedData = searchScreenViewModel.searchResult.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = modifier.padding(10.dp)) {

            OutlinedTextField(
                shape = RoundedCornerShape(53),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,

                    ),

                modifier = modifier
                    .height(49.dp)
                    .padding(0.dp)
                    .fillMaxWidth(0.9f),
                value = searchTerm.trim(),
                onValueChange = { searchTerm = it.trim() },
                placeholder = { Text(text = "Search") },

                )
            OutlinedIconButton(
                onClick = {
                    searchScreenViewModel.getSearchedProduct(searchTerm.trim())
                },
                border = BorderStroke(0.dp, Color.Transparent),

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                    modifier = modifier
                        .height(20.dp)
                        .width(20.dp)
                )

            }
        }

        Spacer(modifier = modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            userScrollEnabled = true,
            modifier = modifier
                .fillMaxHeight()
        ) {
            items(searchedData.value) { item ->
                SearchScreenComponents(
                    imageUrl = item.image,
                    name = item.name,
                    desc = item.desc,
                    onItemClick = {
                        productsViewModel.displayProduct(it)
                        navHostController.navigate(HomeBottomBar.Details.route){
                            launchSingleTop = true
                        }

                    },
                    item = item
                )
            }


        }

    }
}

@Composable
fun SearchScreenComponents(
    modifier: Modifier = Modifier,
    imageUrl: String, name: String,
    desc: String, onItemClick: (product: Product) -> Unit,
    item: Product
) {
    var expanded by remember {
        mutableStateOf(true)
    }

    Card(
        modifier = modifier.padding(1.dp),

        ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = modifier
                    .height(130.dp)
                    .clickable(
                        enabled = true,
                    ) {
                        onItemClick(item)
                    }

            )

            Spacer(modifier = modifier.height(4.dp))

            Text(text = name, fontWeight = FontWeight.Bold)

            Spacer(modifier = modifier.height(4.dp))

            Text(
                text = "Description",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = modifier.height(4.dp))

            Text(
                text = desc,
                maxLines = if (expanded) {
                    3
                } else {
                    13
                },
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .padding(start = 3.dp, end = 3.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .clickable {
                        expanded = !expanded
                    },

                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,

                )

            Spacer(modifier = modifier.height(4.dp))

        }


    }

}

//
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun ShowPrev(){
//    SearchScreen(sharedViewModel}