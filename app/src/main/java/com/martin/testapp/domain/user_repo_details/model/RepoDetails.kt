package com.martin.testapp.domain.user_repo_details.model

import com.martin.testapp.domain.repo_tags.model.RepoTag

data class RepoDetails(
    val id: Int,
    val username: String,
    val forksCount: Int,
    val watchersCount: Int,
    val userDetails: UserDetails,
    var repoTags: List<RepoTag>? = emptyList()
)