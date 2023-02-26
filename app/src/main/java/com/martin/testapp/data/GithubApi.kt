package com.martin.testapp.data

import com.martin.testapp.data.user_repo.model.UserRepoResponse
import com.martin.testapp.data.user_repo_details.model.RepoDetailsResponse
import com.martin.testapp.data.repo_tags.model.RepoTagsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users/octocat/repos")
    suspend fun getUserRepos(): List<UserRepoResponse>
    
    @GET("/repos/octocat/{repo}")
    suspend fun getRepoDetails(@Path("repo") repo: String): RepoDetailsResponse

    @GET("/repos/octocat/{repo}/tags")
    suspend fun getRepoTags(@Path("repo") repo: String): List<RepoTagsResponse>
}