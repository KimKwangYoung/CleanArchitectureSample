package com.kky.cleanarchitecturesample.ui.home

import com.kky.cleanarchitecturesample.ui.base.LoadState
import com.kky.cleanarchitecturesample.ui.base.UiState
import com.kky.domain.model.Keyword
import com.kky.domain.model.Post

data class HomeUiState(
    val loadState: LoadState = LoadState.LOADING,
    val histories: List<Keyword> = emptyList(),
    val posts: List<Post> = emptyList()
): UiState