package com.kky.cleanarchitecturesample.ui.search

import androidx.paging.PagingData
import com.kky.cleanarchitecturesample.ui.base.LoadState
import com.kky.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

data class SearchUiState(
    val loadState: LoadState = LoadState.LOADING,
    val posts: Flow<PagingData<Post>> = emptyFlow()
)
