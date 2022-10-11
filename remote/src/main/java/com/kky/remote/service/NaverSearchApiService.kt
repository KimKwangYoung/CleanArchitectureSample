package com.kky.remote.service

import com.kky.remote.dto.BlogPostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverSearchApiService {
    /* 블로그 검색 결과 */
    @GET("/v1/search/blog.json")
    suspend fun searchBlogPost(
        @Query("query") query: String
    ): BlogPostResponse
}