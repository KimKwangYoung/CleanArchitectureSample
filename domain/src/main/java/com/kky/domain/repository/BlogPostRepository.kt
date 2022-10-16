package com.kky.domain.repository

import com.kky.domain.data.Post

interface BlogPostRepository {
    suspend fun getBlogPost(keyword: String): List<Post>
}