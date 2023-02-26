package com.martin.testapp.ui.screens.user_repo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.martin.testapp.R
import com.martin.testapp.domain.user_repo.model.UserRepo
import com.martin.testapp.ui.compose.DefaultErrorScreen
import com.martin.testapp.ui.compose.DefaultLoadingScreen
import com.martin.testapp.ui.navigation.NavigationScreens
import com.martin.testapp.ui.screens.user_repo.compose_components.UserRepoItem

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UserRepoScreen(
    navController: NavController,
    viewModel: UserRepoViewModel = hiltViewModel(),
) {
    val userRepoUiState by viewModel.userRepoUIState.collectAsStateWithLifecycle()
    UserRepoScreenContent(
        userRepoUiState = userRepoUiState,
        navController = navController,
    )
}

@Composable
private fun UserRepoScreenContent(
    userRepoUiState: UserRepoUiState,
    navController: NavController,
) {
    when(userRepoUiState) {
        is UserRepoUiState.Loading -> DefaultLoadingScreen()
        is UserRepoUiState.Successful -> {
            SuccessfulStateScreenContent(
                userRepos = userRepoUiState.userRepos,
                navController = navController
            )
        }
        is UserRepoUiState.Error -> DefaultErrorScreen(errorMessage = stringResource(id = R.string.error_message))
    }
}

@Composable
private fun SuccessfulStateScreenContent(
    userRepos: List<UserRepo>,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp),
    ) {
        items(userRepos) { repo ->
            UserRepoItem(
                userRepo = repo,
                onItemClick = {
                    navController.navigate(NavigationScreens.UserRepoDetailsScreen.route + "/${repo.name}")
                }
            )
        }
    }
}