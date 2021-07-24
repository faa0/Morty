package com.fara.morty.di

import com.fara.morty.data.dao.ApiService
import com.fara.morty.repo.RemoteRepo
import com.fara.morty.repo.RemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideRemoteRepo(apiService: ApiService): RemoteRepo = RemoteRepoImpl(apiService)
}