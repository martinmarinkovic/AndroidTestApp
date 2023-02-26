package com.martin.testapp.domain.repo_tags

import com.martin.testapp.data.repo_tags.model.RepoTagsResponse

interface RepoTagsRepository {
    suspend fun getRepoTags(repoName: String): List<RepoTagsResponse>
}