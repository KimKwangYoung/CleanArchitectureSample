package com.kky.cleanarchitecturesample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String>
        get() = _error.asSharedFlow()

    protected fun sendError(message: String) {
        viewModelScope.launch {
            _error.emit(message)
        }
    }
}