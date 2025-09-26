package ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.SettingsDataStore
import io.github.smfdrummer.enums.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(dataStore: SettingsDataStore) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.settings.collectLatest { settings ->
                Channel.entries.forEach { channel -> channel.version = settings.version }
            }
        }
    }
}