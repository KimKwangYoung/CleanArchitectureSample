package com.kky.cleanarchitecturesample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kky.domain.data.Post
import com.kky.domain.repository.BlogPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val blogRepository: BlogPostRepository
): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.None)
    val state: LiveData<State>
        get() = _state

    fun fetchBlogData() {
        viewModelScope.launch {
            kotlin.runCatching {
                blogRepository.getBlogPost("나이키")
            }.onSuccess {
                _state.value = State.Success(it)
            }.onFailure {
                _state.value = State.Error(it.message ?: "알 수 없는 에러")
            }
        }
    }


    sealed interface State {
        data class Success(val data: List<Post>): State

        data class Error(val message: String): State

        object None: State
    }
}