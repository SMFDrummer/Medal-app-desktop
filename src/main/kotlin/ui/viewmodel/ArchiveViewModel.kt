package io.github.smfdrummer.medal_app_desktop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.optics.copy
import components.components.snackbar.SnackbarManager
import io.github.smfdrummer.medal_app_desktop.ui.utils.getErrorString
import io.github.smfdrummer.medal_app_desktop.ui.utils.runWith
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.获取存档信息
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.覆盖自定义存档
import io.github.smfdrummer.network.decryptBase64
import io.github.smfdrummer.utils.json.JsonFeature
import io.github.smfdrummer.utils.json.parseJson
import io.github.smfdrummer.utils.strategy.ContextCallback
import io.github.smfdrummer.utils.strategy.StrategyContext
import io.github.smfdrummer.utils.strategy.StrategyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import kotlin.toString

class ArchiveViewModel : ViewModel() {
    private val _archive = MutableStateFlow<Archive?>(null)
    val archive: StateFlow<Archive?> get() = _archive.asStateFlow()

    private val _origin = MutableStateFlow<Archive?>(null)
    val origin: StateFlow<Archive?> get() = _origin.asStateFlow()

    private val _packetLog = MutableStateFlow<List<JsonObject>>(emptyList())
    val packetLog: StateFlow<List<JsonObject>> get() = _packetLog.asStateFlow()

    private val _context = MutableStateFlow(
        StrategyContext(
            callback = object : ContextCallback {
                override fun onPacketStart(packetId: String, request: JsonObject) {
                    _packetLog.tryEmit(_packetLog.value + request)
                }

                override fun onPacketSuccess(packetId: String, response: JsonObject) {
                    _packetLog.tryEmit(_packetLog.value + response)
                }

                override fun onPacketFailure(
                    packetId: String,
                    error: StrategyException
                ) {
                    SnackbarManager.showSnackbar(
                        "发送 $packetId 数据包失败：${error.getErrorString()}"
                    )
                }

                override fun onPacketRetry(
                    packetId: String,
                    attempt: Int,
                    error: StrategyException
                ) {
                    SnackbarManager.showSnackbar(
                        "重试 $packetId 数据包第 $attempt 次失败：${error.getErrorString()}"
                    )
                }

                override fun onStrategyComplete(success: Boolean) {
                    SnackbarManager.showSnackbar(
                        "执行完成，返回值：$success"
                    )
                }

            }
        )
    )
    val context: StateFlow<StrategyContext> get() = _context.asStateFlow()

    private val _isCredentialValid = MutableStateFlow(false)
    val isCredentialValid: StateFlow<Boolean> get() = _isCredentialValid.asStateFlow()

    fun setArchive(context: StrategyContext = _context.value) {
        with(context) {
            val p = variables["p"].takeIf { it !is JsonNull }
            val s = variables["s"].takeIf { it !is JsonNull }
            if (p != null && s != null) {
                _archive.update {
                    Archive(
                        p = p.jsonPrimitive.content.decryptBase64().decodeToString().parseJson(),
                        s = s.jsonPrimitive.content.decryptBase64().decodeToString().parseJson()
                    )
                }
            }
        }
    }

    private fun calculateValid() {
        _isCredentialValid.value = with(context.value.variables) {
            containsKey("pi") && containsKey("sk") && containsKey("ui")
        }
    }

    fun modifyContext(vararg variable: Pair<String, JsonElement>) {
        _context.update { context ->
            variable.forEach {
                context.variables[it.first] = it.second
            }
            context
        }
        calculateValid()
    }

    fun modifyArchive(transformer: (JsonElement) -> JsonElement) {
        _archive.update { current ->
            current?.copy(p = transformer(current.p))
        }
    }

    fun setOrigin() {
        _archive.value?.let {
            _origin.value = it
        }
    }

    fun obtainArchive(
        channel: Int,
        phoneOrUserId: String? = null,
        password: String? = null,
    ) = viewModelScope.launch(Dispatchers.IO) {
        获取存档信息().runWith(
            channel = channel,
            phoneOrUserId = phoneOrUserId,
            password = password,
            context = context.value
        ).onSuccess {
            calculateValid()
            setArchive()
            setOrigin()
        }
    }

    fun requestArchive(
        channel: Int,
        phoneOrUserId: String? = null,
        password: String? = null,
    ) = viewModelScope.launch(Dispatchers.IO) {
        _archive.value?.let { archive ->
            覆盖自定义存档(archive.p, archive.s).runWith(
                channel = channel,
                phoneOrUserId = phoneOrUserId,
                password = password,
                context = context.value
            ).onSuccess {
                setOrigin()
            }
        }
    }

    fun clearAll() {
        _archive.value = null
        _origin.value = null
        _context.update {
            it.apply {
                variables.clear()
                responses.clear()
            }
        }
    }
}

data class Archive(
    val p: JsonElement,
    val s: JsonElement,
)