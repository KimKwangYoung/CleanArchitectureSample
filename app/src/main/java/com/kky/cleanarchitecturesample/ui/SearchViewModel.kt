package com.kky.cleanarchitecturesample.ui

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kky.cleanarchitecturesample.ui.state.State
import com.kky.domain.repository.BlogPostRepository
import com.kky.remote.service.NaverSearchApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPostRepository: BlogPostRepository
): ViewModel(), TextView.OnEditorActionListener {

    private val _state:MutableLiveData<State> = MutableLiveData(State.None)
    val state: LiveData<State>
        get() = _state

    private val _event: SingleLiveEvent<SearchEvent> = SingleLiveEvent()
    val event: LiveData<SearchEvent>
        get() = _event

    fun search(keyword: String) {
        if (keyword.isBlank()) {
            _event.value = SearchEvent.EMPTY_KEYWORD
            return
        }

        viewModelScope.launch {
            kotlin.runCatching {
                searchPostRepository.getBlogPost(keyword)
            }.onSuccess {
                _state.value = State.Success(it)
            }.onFailure {
                _state.value = State.Error(it.message ?: "알 수 없는 에러")
            }
        }
    }

    enum class SearchEvent {
        EMPTY_KEYWORD
    }

    override fun onEditorAction(textView: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search(textView?.text?.toString() ?: "")
            return true
        }

        return false
    }
}