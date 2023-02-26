package com.martin.testapp.data.repo_tags

import com.martin.testapp.data.GithubApi
import com.martin.testapp.data.repo_tags.model.RepoTagsResponse
import com.martin.testapp.domain.repo_tags.RepoTagsRepository
import javax.inject.Inject

class RepoTagsRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : RepoTagsRepository {
    override suspend fun getRepoTags(repoName: String): List<RepoTagsResponse> = api.getRepoTags(repoName)
}