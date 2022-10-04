package com.kky.remote.di

import com.kky.remote.dto.BlogPostDTO
import retrofit2.http.GET

internal interface NaverSearchApiService {
    /* 블로그 검색 결과 */
    @GET("/v1/search/blog.json")
    suspend fun searchBlogPost(): List<BlogPostDTO>
}