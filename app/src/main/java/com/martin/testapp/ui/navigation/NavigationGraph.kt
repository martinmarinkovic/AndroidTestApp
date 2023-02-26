package com.martin.testapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.testapp.ui.screens.user_repo.UserRepoScreen
import com.martin.testapp.ui.screens.user_repo_details.UserRepoDetailsScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.UserRepoScreen.route
    ) {
        composable(
            route = NavigationScreens.UserRepoScreen.route
        ) {
            UserRepoScreen(navController = navController)
        }
        composable(
            route = NavigationScreens.UserRepoDetailsScreen.route + "/{repoName}"
        ) {
            UserRepoDetailsScreen()
        }
    }
}