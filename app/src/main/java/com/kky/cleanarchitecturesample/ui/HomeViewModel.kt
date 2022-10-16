package com.kky.cleanarchitecturesample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kky.cleanarchitecturesample.ui.state.State
import com.kky.domain.repository.BlogPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val blogRepository: BlogPostRepository
): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.None)
    val state: LiveData<State>
        get() = _state

    private val _event: SingleLiveEvent<HomeEvent> = SingleLiveEvent()
    val event: LiveData<HomeEvent>
        get() = _event


    fun navigateSearch() {
        _event.value = HomeEvent.NAVIGATE_SEARCH
    }

    enum class HomeEvent {
        NAVIGATE_SEARCH
    }
}