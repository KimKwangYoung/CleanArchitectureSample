package com.kky.remote.di

import com.kky.domain.repository.BlogPostRepository
import com.kky.remote.repository.BlogPostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsBlogPostRepository(impl: BlogPostRepositoryImpl): BlogPostRepository
}