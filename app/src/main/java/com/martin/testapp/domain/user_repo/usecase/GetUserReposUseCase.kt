package com.martin.testapp.domain.user_repo.usecase

import com.martin.testapp.domain.user_repo.UserRepoRepository
import com.martin.testapp.domain.user_repo.model.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.martin.testapp.common.Result
import com.martin.testapp.data.user_repo.model.mapToUserRepo
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userRepoRepository: UserRepoRepository
) {
    operator fun invoke(): Flow<Result<List<UserRepo>>> = flow {
        emit(Result.Loading)
        runCatching {
            userRepoRepository.getUserRepos()
        }.mapCatching { response ->
            response.map { userRepoResponse -> userRepoResponse.mapToUserRepo() }
        }.mapCatching { userRepos ->
            emit(Result.Success(userRepos))
        }.onFailure {
            emit(Result.Error(it))
        }
    }
}