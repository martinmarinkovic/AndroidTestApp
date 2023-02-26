package com.martin.testapp.domain.user_repo_details.usecase

import com.martin.testapp.data.user_repo_details.model.mapToRepoDetails
import com.martin.testapp.domain.user_repo_details.UserRepoDetailsRepository
import com.martin.testapp.domain.user_repo_details.model.RepoDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserRepoDetailsUseCase @Inject constructor(
    private val repoDetailsRepository: UserRepoDetailsRepository,
) {
    suspend operator fun invoke(repoName: String): Flow<RepoDetails> = flow{
        runCatching {
            repoDetailsRepository.getRepoDetails(repoName = repoName)
        }.mapCatching { response ->
            emit(response.mapToRepoDetails())
        }
    }
}