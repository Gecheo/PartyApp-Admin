package com.partyapp_admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.partyapp_admin.utils.AUTHENTICATION
import com.partyapp_admin.utils.CONTENT
import com.partyapp_admin.utils.ROOT

sealed class RootGraph (val route : String){
    data object Root : RootGraph(ROOT)
    data object Content : RootGraph(CONTENT)
    data object Authentication : RootGraph(AUTHENTICATION)
}

@Composable
fun RootNavGraph(){
    val navController = rememberNavController()
    val navHostController = rememberNavController()

    NavHost(navController = navController ,
        startDestination = RootGraph.Authentication.route,
        route = RootGraph.Root.route  ){

        loginGraph(navController)
        composable(route = RootGraph.Content.route){
            Content(navController = navHostController)
        }




    }
}