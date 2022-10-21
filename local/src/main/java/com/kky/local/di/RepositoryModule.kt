package com.kky.local.di

import com.kky.domain.repository.KeywordRepository
import com.kky.local.repository.KeywordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsKeywordRepository(impl: KeywordRepositoryImpl): KeywordRepository
}