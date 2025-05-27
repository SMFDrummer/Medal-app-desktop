package io.github.smfdrummer.medal_app_desktop.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.smfdrummer.utils.strategy.StrategyConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StrategyViewModel : ViewModel() {
    private val _callbackList: MutableStateFlow<List<StrategyCallback>> = MutableStateFlow(emptyList())
    val callbackList: StateFlow<List<StrategyCallback>> get() =  _callbackList.asStateFlow()

    fun sendCallback(packetId: String? = null, status: CardStatus, content: String) {
        _callbackList.tryEmit(_callbackList.value + StrategyCallback(packetId, status, content))
    }

    init {
        sendCallback("Medal Terminal", CardStatus.NORMAL, "欢迎使用 Medal Terminal\n下面会记录所有的策略运行状态信息")
    }

    fun clearCallbacks() {
        _callbackList.tryEmit(emptyList())
        sendCallback("Medal Terminal", CardStatus.NORMAL, "欢迎使用 Medal Terminal\n下面会记录所有的策略运行状态信息")
    }

    private val _runningStatus = MutableStateFlow(RunningStatus.PENDING)
    val runningStatus: StateFlow<RunningStatus> get() = _runningStatus

    fun setRunning(status: RunningStatus) { _runningStatus.value = status }

    private val _currentAccountPath = MutableStateFlow<String?>(null)
    val currentAccountPath: StateFlow<String?> get() = _currentAccountPath.asStateFlow()

    private val _currentStrategy = MutableStateFlow<StrategyConfig?>(null)
    val currentStrategy: StateFlow<StrategyConfig?> get() = _currentStrategy.asStateFlow()

    private val _currentCutoff = MutableStateFlow<((Int) -> Boolean)?>(null)
    val currentCutoff: StateFlow<((Int) -> Boolean)?> get() = _currentCutoff.asStateFlow()

    fun setCurrentStrategy(accountPath: String, strategy: StrategyConfig, cutoff: ((Int) -> Boolean)? = null) {
        _currentAccountPath.value = accountPath
        _currentStrategy.value = strategy
        _currentCutoff.value = cutoff
    }

    fun clearCurrentStrategy() {
        _currentAccountPath.value = null
        _currentStrategy.value = null
        _currentCutoff.value = null
    }
}

data class StrategyCallback(
    val packetId: String? = null,
    val status: CardStatus,
    val content: String
)

enum class CardStatus {
    NONE, LOG, NORMAL, ERROR, SUCCESS
}

enum class RunningStatus {
    PENDING, RUNNING, COMPLETE
}