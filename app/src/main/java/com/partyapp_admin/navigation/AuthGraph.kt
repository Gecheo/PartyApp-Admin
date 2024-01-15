package com.partyapp_admin.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.partyapp_admin.screens.EntryScreen
import com.partyapp_admin.screens.ForgotScreen
import com.partyapp_admin.screens.LoginScreen
import com.partyapp_admin.screens.RegisterScreen
import com.partyapp_admin.utils.FORGOT
import com.partyapp_admin.utils.LOGIN
import com.partyapp_admin.utils.SIGNUP
import com.partyapp_admin.utils.SPLASH
import com.partyapp_admin.viewModels.LoginViewModel
import com.partyapp_admin.viewModels.SignUpViewModel


sealed class AuthGraph(val route:String){
    data object SplashScreen:AuthGraph(SPLASH)
    data object SignupPage:AuthGraph(SIGNUP)
    data object LoginPage: AuthGraph(LOGIN)
    data object ForgotPage: AuthGraph(FORGOT)

}

fun NavGraphBuilder.loginGraph(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel){
    navigation(startDestination =AuthGraph.SignupPage.route,route = RootGraph.Authentication.route){
        composable(route = AuthGraph.SplashScreen.route){
            EntryScreen(navController = navController)
        }
        composable(route = AuthGraph.SignupPage.route){
            RegisterScreen(navHostController = navController, signUpViewModel = signUpViewModel)
        }
        composable(route = AuthGraph.LoginPage.route){
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(route = AuthGraph.ForgotPage.route){
            ForgotScreen()
        }
    }


}