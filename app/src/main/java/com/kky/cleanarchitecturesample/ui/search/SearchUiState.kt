package com.kky.cleanarchitecturesample.ui.search

import com.kky.cleanarchitecturesample.ui.base.LoadState
import com.kky.domain.model.Post

data class SearchUiState(
    val loadState: LoadState = LoadState.LOADING,
    val posts: List<Post> = emptyList()
)
