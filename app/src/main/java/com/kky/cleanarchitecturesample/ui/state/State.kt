package com.kky.cleanarchitecturesample.ui.state

import com.kky.domain.data.Post

sealed interface State {
    data class Success(val data: List<Post>): State

    data class Error(val message: String): State

    object None: State
}