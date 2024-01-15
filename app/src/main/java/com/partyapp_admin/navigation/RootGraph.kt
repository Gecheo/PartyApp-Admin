package com.partyapp_admin.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.partyapp_admin.utils.AUTHENTICATION
import com.partyapp_admin.utils.CONTENT
import com.partyapp_admin.utils.ROOT

import com.partyapp_admin.viewModels.LoginViewModel
import com.partyapp_admin.viewModels.OrdersViewModel
import com.partyapp_admin.viewModels.ProductsViewModel
import com.partyapp_admin.viewModels.SearchScreenViewModel
import com.partyapp_admin.viewModels.SignUpViewModel
import com.partyapp_admin.viewModels.UpdateProductViewModel

sealed class RootGraph(val route: String) {
    data object Root : RootGraph(ROOT)
    data object Content : RootGraph(CONTENT)
    data object Authentication : RootGraph(AUTHENTICATION)
}

@Composable
fun RootNavGraph(user: FirebaseUser? = FirebaseAuth.getInstance().currentUser) {
    val navController = rememberNavController()
    val navHostController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    val productsViewModel: ProductsViewModel = hiltViewModel()
    val ordersViewModel: OrdersViewModel = hiltViewModel()
    val searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
    val addItemViewModel: UpdateProductViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = if (user?.uid != null) {
            RootGraph.Content.route
        } else {
            RootGraph.Authentication.route
        },
        route = RootGraph.Root.route
    ) {
        loginGraph(
            navController = navController,
            signUpViewModel = signUpViewModel,
            loginViewModel = loginViewModel
        )
        composable(route = RootGraph.Content.route) {
            Content(
                navController = navHostController,
                productsViewModel = productsViewModel,
                ordersViewModel = ordersViewModel,
                searchScreenViewModel = searchScreenViewModel,
                updateProductViewModel = addItemViewModel
            )
        }


    }
}