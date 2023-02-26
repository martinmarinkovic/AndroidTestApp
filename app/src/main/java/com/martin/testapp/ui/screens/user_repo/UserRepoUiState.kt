package com.martin.testapp.ui.screens.user_repo

import com.martin.testapp.domain.user_repo.model.UserRepo

sealed interface UserRepoUiState {
    object Loading : UserRepoUiState

    data class Successful(
        val userRepos: List<UserRepo>,
    ) : UserRepoUiState

    object Error : UserRepoUiState
}