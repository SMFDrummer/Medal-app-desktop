package io.github.smfdrummer.medal_app_desktop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExperimentViewModel : ViewModel() {
    private val _log = MutableStateFlow(listOf<String>())
    val log = _log.asStateFlow()
    fun i(message: String) {
        _log.value = _log.value + message
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}