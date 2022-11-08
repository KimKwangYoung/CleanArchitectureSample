package com.kky.remote.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kky.domain.model.Post
import com.kky.remote.service.NaverSearchApiService
import javax.inject.Inject

class PostPagingResource @Inject constructor(
    private val postRepository: NaverSearchApiService,
    private val keyword: String
): PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val loadCount = params.loadSize
        val page = params.key ?: 1

        val response = postRepository.searchBlogPost(query = keyword, page = page, count = loadCount).items.map { it.toDomainModel() }
        return LoadResult.Page(
            data = response,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == 100) null else page + 1
        )
    }
}