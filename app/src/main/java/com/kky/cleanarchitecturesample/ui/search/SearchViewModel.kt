package com.kky.cleanarchitecturesample.ui.search

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kky.cleanarchitecturesample.ui.base.BaseViewModel
import com.kky.cleanarchitecturesample.ui.base.LoadState
import com.kky.domain.model.Keyword
import com.kky.domain.repository.BlogPostRepository
import com.kky.domain.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPostRepository: BlogPostRepository,
    private val keywordRepository: KeywordRepository
): BaseViewModel(), TextView.OnEditorActionListener {

    private val _state:MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState>
        get() = _state.asStateFlow()

    private val _event: MutableSharedFlow<SearchEvent> = MutableSharedFlow()
    val event: SharedFlow<SearchEvent>
        get() = _event

    fun search(keyword: String) {
        if (keyword.isBlank()) {
            viewModelScope.launch { _event.emit(SearchEvent.EMPTY_KEYWORD) }
            return
        }

        viewModelScope.launch {
            kotlin.runCatching {
                searchPostRepository.getBlogPost(keyword)
            }.onSuccess { posts ->
                _state.value = _state.value.copy(loadState = LoadState.LOADING, posts = posts)
                saveKeyword(keyword)
            }.onFailure {
                sendError(it.message ?: "알 수 없는 에러")
            }
        }
    }

    private fun saveKeyword(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val keyword: Keyword? = keywordRepository.search(value)
            keyword?.let {
                keywordRepository.addCount(it)
            } ?: kotlin.run {
                keywordRepository.insert(value)
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