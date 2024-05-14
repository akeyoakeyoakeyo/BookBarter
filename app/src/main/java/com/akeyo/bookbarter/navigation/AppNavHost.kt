package com.akeyo.bookbarter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.ui.theme.screens.auth.LoginScreen
import com.akeyo.bookbarter.ui.theme.screens.auth.RegisterScreen
import com.akeyo.bookbarter.ui.theme.screens.book.AddBooksScreen
import com.akeyo.bookbarter.ui.theme.screens.book.BookListScreen
import com.akeyo.bookbarter.ui.theme.screens.book.EditBooksScreen
import com.akeyo.bookbarter.ui.theme.screens.home.HomeScreen
import com.akeyo.bookbarter.ui.theme.screens.userprofile.UserProfileScreen


@Composable
fun AppNavHost (modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination: String = ROUTE_HOME){

    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){

        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_USER_PROFILE){
            UserProfileScreen(navController)
        }
        composable(ROUTE_ADD_BOOK){
            AddBooksScreen(navController)
        }
        composable(ROUTE_EDIT_BOOK){
            EditBooksScreen(navController, id ="" )
        }
        composable(ROUTE_BOOK_LIST){
            BookListScreen(navController)
        }
//        composable(ROUTE_BOOK_DETAIL){
//            BookDetailScreen(navController)
//        }





    }





}

