package com.martin.testapp.domain.repo_tags.usecase

import com.martin.testapp.data.repo_tags.model.mapToRepoTag
import com.martin.testapp.domain.repo_tags.RepoTagsRepository
import com.martin.testapp.domain.repo_tags.model.RepoTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepoTagsUseCase @Inject constructor(
    private val repoTagsRepository: RepoTagsRepository
) {
    suspend operator fun invoke(repoName: String): Flow<List<RepoTag>> = flow{
        runCatching {
            repoTagsRepository.getRepoTags(repoName = repoName)
        }.mapCatching { response ->
            emit(response.map {repoTagsResponse -> repoTagsResponse.mapToRepoTag()})
        }
    }
}