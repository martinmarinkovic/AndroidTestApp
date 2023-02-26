package com.martin.testapp.data.user_repo_details

import com.martin.testapp.data.GithubApi
import com.martin.testapp.data.user_repo_details.model.RepoDetailsResponse
import com.martin.testapp.domain.user_repo_details.UserRepoDetailsRepository
import javax.inject.Inject

class UserRepoDetailsRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UserRepoDetailsRepository {
   override suspend fun getRepoDetails(repoName: String): RepoDetailsResponse = api.getRepoDetails(repoName)
}