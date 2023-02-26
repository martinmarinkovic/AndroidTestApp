package com.martin.testapp.ui.navigation

sealed class NavigationScreens(val route: String) {
    object UserRepoScreen: NavigationScreens("user_repos")
    object UserRepoDetailsScreen: NavigationScreens("user_repo_details")
}