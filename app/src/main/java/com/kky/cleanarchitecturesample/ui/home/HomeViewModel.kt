package com.kky.cleanarchitecturesample.ui.home

import androidx.lifecycle.viewModelScope
import com.kky.cleanarchitecturesample.ui.base.BaseViewModel
import com.kky.cleanarchitecturesample.ui.base.LoadState
import com.kky.domain.repository.KeywordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val keywordRepository: KeywordRepository
): BaseViewModel() {

    private val _state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState>
        get() = _state.asStateFlow()

    private val _event: MutableSharedFlow<HomeEvent> = MutableSharedFlow()
    val event: SharedFlow<HomeEvent>
        get() = _event

    init {
        observeHistory()
    }

    private fun observeHistory() {
        viewModelScope.launch {
            keywordRepository.getAll().collect {
                _state.value = _state.value.copy(loadState = LoadState.SUCCESS, histories = it)
            }
        }
    }

    fun navigateSearch() {
        viewModelScope.launch {
            _event.emit(HomeEvent.NAVIGATE_SEARCH)
        }
    }

    enum class HomeEvent {
        NAVIGATE_SEARCH
    }
}