package com.kky.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kky.domain.model.Post
import com.kky.domain.repository.BlogPostRepository
import com.kky.remote.pagingSource.PostPagingResource
import com.kky.remote.service.NaverSearchApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlogPostRepositoryImpl @Inject constructor(
    private val naverApiService: NaverSearchApiService
): BlogPostRepository {
    override suspend fun getBlogPost(keyword: String): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = 30)){
            PostPagingResource(naverApiService, keyword)
        }.flow
    }
}