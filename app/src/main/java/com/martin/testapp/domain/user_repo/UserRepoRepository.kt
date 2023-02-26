package com.martin.testapp.domain.user_repo

import com.martin.testapp.data.user_repo.model.UserRepoResponse

interface UserRepoRepository {
    suspend fun getUserRepos(): List<UserRepoResponse>
}