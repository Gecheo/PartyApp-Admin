package com.partyapp_admin.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.partyapp_admin.R
import com.partyapp_admin.screens.AdminAddImage
import com.partyapp_admin.screens.OrdersPage
import com.partyapp_admin.screens.HomePage
import com.partyapp_admin.screens.SearchScreen
import com.partyapp_admin.utils.ADD_PRODUCT
import com.partyapp_admin.utils.DETAILS
import com.partyapp_admin.utils.HOME
import com.partyapp_admin.utils.ORDERS
import com.partyapp_admin.utils.SEARCH_PRODUCT

import com.partyapp_admin.viewModels.OrdersViewModel
import com.partyapp_admin.viewModels.ProductsViewModel
import com.partyapp_admin.viewModels.SearchScreenViewModel
import com.partyapp_admin.viewModels.UpdateProductViewModel

@Composable
fun Content(
    navController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel,
    productsViewModel: ProductsViewModel,
    ordersViewModel: OrdersViewModel,
    updateProductViewModel: UpdateProductViewModel

) {

    NavHost(
        navController = navController,
        startDestination = HomeBottomBar.Home.route,
        route = RootGraph.Content.route
    ) {
        composable(route = HomeBottomBar.Home.route) {
            HomePage(navController = navController, productsViewModel = productsViewModel)
        }
        composable(route = HomeBottomBar.Account.route) {
            OrdersPage(navController = navController, ordersViewModel = ordersViewModel)
        }
        composable(route = HomeBottomBar.SearchProduct.route) {
            SearchScreen(
                navHostController = navController,
                searchScreenViewModel = searchScreenViewModel,
                productsViewModel = productsViewModel
            )

        }
        composable(route = HomeBottomBar.AddProduct.route) {
            AdminAddImage(updateProductViewModel = updateProductViewModel)
        }

        composable(route = HomeBottomBar.Details.route) {
            DetailsGraph(productsViewModel = productsViewModel, updateProductViewModel = updateProductViewModel)
        }


    }
}


sealed class HomeBottomBar(
    val route: String,
    val icon: Int?
) {
    data object Home : HomeBottomBar(
        route = HOME,
        icon = R.drawable.home_button
    )

    data object Account : HomeBottomBar(
        route = ORDERS,
        icon = R.drawable.baseline_auto_stories_24
    )

    data object Details : HomeBottomBar(
        route = DETAILS,
        icon = null
    )

    data object SearchProduct : HomeBottomBar(
        route = SEARCH_PRODUCT,
        icon = null
    )

    data object AddProduct : HomeBottomBar(
        route = ADD_PRODUCT,
        icon = null
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val screens = listOf(
        HomeBottomBar.Home, HomeBottomBar.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.BottomCenter)
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }

                        }
                        launchSingleTop = true
                    }


                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon!!),
                        contentDescription = null, modifier = modifier
                            .size(width = 24.dp, height = 24.dp)
                    )

                })
        }
    }
}


