package com.kky.domain.repository

import com.kky.domain.data.BlogPost

interface BlogPostRepository {
    suspend fun getBlogPost(keyword: String): List<BlogPost>
}