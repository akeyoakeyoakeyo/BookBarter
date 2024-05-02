package com.akeyo.bookbarter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.ui.theme.screens.auth.LoginScreen
import com.akeyo.bookbarter.ui.theme.screens.auth.RegisterScreen
import com.akeyo.bookbarter.ui.theme.screens.book.AddBooksScreen
import com.akeyo.bookbarter.ui.theme.screens.home.HomeScreen
import com.akeyo.bookbarter.ui.theme.screens.userprofile.UserProfileScreen


@Composable
fun AppNavHost (modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination: String = ROUTE_HOME) {

    composable(ROUTE_HOME){
        HomeScreen(navController)

    composable(ROUTE_LOGIN){
        LoginScreen(navController)
    }
    composable(ROUTE_REGISTER){
        RegisterScreen(navController)
    }
    composable(ROUTE_ADD_BOOK)
        AddBooksScreen(navController)
    }
    composable(ROUTE_USER_PROFILE)
        UserProfileScreen(navController)
    }



}