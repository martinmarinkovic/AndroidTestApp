package com.martin.testapp.ui.screens.user_repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.testapp.common.Result.*
import com.martin.testapp.domain.user_repo.usecase.GetUserReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepoViewModel @Inject constructor(
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {

    private val _userRepoUIState: MutableStateFlow<UserRepoUiState> = MutableStateFlow(
        UserRepoUiState.Loading
    )
    val userRepoUIState: StateFlow<UserRepoUiState> = _userRepoUIState.asStateFlow()

    private fun getUserRepos() {
        viewModelScope.launch {
            getUserReposUseCase().collect { result ->
                when(result) {
                    is Loading -> _userRepoUIState.value = UserRepoUiState.Loading
                    is Success -> _userRepoUIState.value =
                        UserRepoUiState.Successful(userRepos = result.data)
                    is Error -> _userRepoUIState.value = UserRepoUiState.Error
                }
            }
        }
    }

    init {
        getUserRepos()
    }
}