package ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.smfdrummer.enums.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            Channel.entries.forEach { channel -> channel.version = "3.7.6" }
        }
    }
}