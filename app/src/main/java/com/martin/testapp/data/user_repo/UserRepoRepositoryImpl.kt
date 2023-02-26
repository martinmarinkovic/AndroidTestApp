package com.martin.testapp.data.user_repo

import com.martin.testapp.data.GithubApi
import com.martin.testapp.data.user_repo.model.UserRepoResponse
import com.martin.testapp.domain.user_repo.UserRepoRepository
import javax.inject.Inject

class UserRepoRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UserRepoRepository {
    override suspend fun getUserRepos(): List<UserRepoResponse> = api.getUserRepos()
}