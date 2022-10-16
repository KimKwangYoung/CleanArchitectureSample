package com.kky.remote.repository

import com.kky.domain.data.Post
import com.kky.domain.repository.BlogPostRepository
import com.kky.remote.service.NaverSearchApiService
import javax.inject.Inject

class BlogPostRepositoryImpl @Inject constructor(
    private val naverApiService: NaverSearchApiService
): BlogPostRepository {
    override suspend fun getBlogPost(keyword: String): List<Post> {
        return naverApiService.searchBlogPost(keyword).items.map { it.toDomainModel() }
    }
}