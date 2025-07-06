package io.github.smfdrummer.medal_app_desktop.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExperimentViewModel : ViewModel() {
    private val _log = MutableStateFlow(listOf<String>())
    val log = _log.asStateFlow()
    fun i(message: String) {
        _log.value = _log.value + message
    }
}