package com.kky.domain.repository

import androidx.paging.PagingData
import com.kky.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface BlogPostRepository {
    suspend fun getBlogPost(keyword: String): Flow<PagingData<Post>>
}