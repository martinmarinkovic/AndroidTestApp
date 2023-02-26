package com.martin.testapp.ui.screens.user_repo_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.testapp.common.Constants.PARAM_REPO_NAME
import com.martin.testapp.common.Result.*
import com.martin.testapp.common.asResult
import com.martin.testapp.domain.repo_tags.usecase.GetRepoTagsUseCase
import com.martin.testapp.domain.user_repo_details.usecase.GetUserRepoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepoDetailsViewModel @Inject constructor(
    private val getUserRepoDetailsUseCase: GetUserRepoDetailsUseCase,
    private val getRepoTagsUseCase: GetRepoTagsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userRepoDetailsUiState: MutableStateFlow<UserRepoDetailsUiState> = MutableStateFlow(UserRepoDetailsUiState.Loading)
    val userRepoDetailsUiState: StateFlow<UserRepoDetailsUiState> = _userRepoDetailsUiState.asStateFlow()

    private fun getUserRepoDetails(repo: String) {
        viewModelScope.launch {
            getUserRepoDetailsUseCase(repo).combine(getRepoTagsUseCase(repo)) { details, tags ->
                details.also { it.repoTags = tags }
            }.asResult().collect{ result ->
                when(result) {
                    is Loading -> _userRepoDetailsUiState.value = UserRepoDetailsUiState.Loading
                    is Success -> _userRepoDetailsUiState.value = UserRepoDetailsUiState.Successful(result.data)
                    is Error -> _userRepoDetailsUiState.value = UserRepoDetailsUiState.Error
                }
            }
        }
    }

    init {
        savedStateHandle.get<String>(PARAM_REPO_NAME)?.let { repoName ->
            getUserRepoDetails(repoName)
        }
    }
}