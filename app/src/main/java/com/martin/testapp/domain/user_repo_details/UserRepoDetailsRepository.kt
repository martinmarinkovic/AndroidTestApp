package com.martin.testapp.domain.user_repo_details

import com.martin.testapp.data.user_repo_details.model.RepoDetailsResponse

interface UserRepoDetailsRepository {
    suspend fun getRepoDetails(repoName: String): RepoDetailsResponse
}