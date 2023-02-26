package com.martin.testapp.ui.screens.user_repo_details

import com.martin.testapp.domain.user_repo_details.model.RepoDetails

sealed interface UserRepoDetailsUiState {
    object Loading : UserRepoDetailsUiState

    data class Successful(
        val repoDetails: RepoDetails,
    ) : UserRepoDetailsUiState

    object Error : UserRepoDetailsUiState
}