package com.martin.testapp.data.repo_tags.model

import com.martin.testapp.domain.repo_tags.model.RepoTag

data class RepoTagsResponse(
    val commit: Commit,
    val name: String,
    val node_id: String,
    val tarball_url: String,
    val zipball_url: String
)

fun RepoTagsResponse.mapToRepoTag() : RepoTag {
    return RepoTag(
        name = name,
        commitSha = commit.sha
    )
}