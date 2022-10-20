package com.kky.domain.repository

import com.kky.domain.model.Post

interface BlogPostRepository {
    suspend fun getBlogPost(keyword: String): List<Post>
}