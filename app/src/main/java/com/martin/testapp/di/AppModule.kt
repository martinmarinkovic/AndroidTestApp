package com.martin.testapp.di

import com.martin.testapp.common.Constants
import com.martin.testapp.data.GithubApi
import com.martin.testapp.data.repo_tags.RepoTagsRepositoryImpl
import com.martin.testapp.data.user_repo.UserRepoRepositoryImpl
import com.martin.testapp.data.user_repo_details.UserRepoDetailsRepositoryImpl
import com.martin.testapp.domain.repo_tags.RepoTagsRepository
import com.martin.testapp.domain.user_repo.UserRepoRepository
import com.martin.testapp.domain.user_repo_details.UserRepoDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepoRepository(api: GithubApi): UserRepoRepository {
        return UserRepoRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserDetailsRepoRepository(api: GithubApi): UserRepoDetailsRepository {
        return UserRepoDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepoTagsRepository(api: GithubApi): RepoTagsRepository {
        return RepoTagsRepositoryImpl(api)
    }
}