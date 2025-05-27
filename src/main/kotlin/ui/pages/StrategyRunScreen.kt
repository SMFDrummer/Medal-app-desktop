package ui.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.AlertDialog
import components.components.Badge
import components.components.Button
import components.components.ButtonVariant
import components.components.HorizontalDivider
import components.components.Icon
import components.components.IconButton
import components.components.IconButtonVariant
import components.components.Text
import components.components.VerticalDivider
import components.components.card.Card
import components.components.card.CardDefaults
import components.components.card.DashBoardCard
import components.components.card.ElevatedCard
import components.contentColorFor
import data.AppSettings
import data.SettingsDataStore
import io.github.smfdrummer.medal_app_desktop.ui.utils.User
import io.github.smfdrummer.medal_app_desktop.ui.utils.runWith
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.CardStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.RunningStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.StrategyViewModel
import io.github.smfdrummer.network.decryptResponse
import io.github.smfdrummer.utils.json.JsonFeature
import io.github.smfdrummer.utils.json.jsonWith
import io.github.smfdrummer.utils.strategy.ContextCallback
import io.github.smfdrummer.utils.strategy.StrategyConfig
import io.github.smfdrummer.utils.strategy.StrategyException
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@Composable
fun StrategyRunScreen(
    accountPath: String,
    strategy: StrategyConfig,
    onBack: () -> Unit,
    additionalCutoff: ((Int) -> Boolean)? = null
) {
    val scope = rememberCoroutineScope()
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    val strategyViewModel: StrategyViewModel = koinViewModel()
    val callbackList by strategyViewModel.callbackList.collectAsState()
    val runningStatus by strategyViewModel.runningStatus.collectAsState()

    // 设置当前策略信息
    LaunchedEffect(Unit) {
        strategyViewModel.setCurrentStrategy(accountPath, strategy, additionalCutoff)
    }

    // 清理当前策略信息
    DisposableEffect(Unit) {
        onDispose {
            if (runningStatus != RunningStatus.RUNNING) {
                strategyViewModel.clearCurrentStrategy()
            }
        }
    }

    var currentPacketId by remember { mutableStateOf<String?>(null) }
    var currentUser by remember { mutableStateOf<User?>(null) }

    fun StrategyException.getErrorString() = when(this) {
        is StrategyException.UnknownRetryError -> "未知重试错误"
        is StrategyException.TemplateRenderError -> "模板渲染错误: $key"
        is StrategyException.InvalidActionValue -> "无效的操作值: $value"
        is StrategyException.NetworkError -> "网络错误: ${cause.message}"
        is StrategyException.DecryptionError -> "解密错误: ${cause.message}"
        is StrategyException.UnexpectedResponseCode -> "意外的响应码: 期望 $expect, 实际 $actual"
        is StrategyException.CredentialExpired -> "凭证已过期: 错误码 $code"
        is StrategyException.CredentialRefreshError -> "凭证刷新错误: ${cause.message}"
    }

    val contextCallback = remember {
        object : ContextCallback {
            override fun onPacketStart(packetId: String) {
                currentPacketId = packetId
                strategyViewModel.sendCallback(currentPacketId, CardStatus.NORMAL, "开始执行")
            }

            override fun onPacketSuccess(packetId: String, response: JsonObject) {
                currentPacketId = packetId
                strategyViewModel.sendCallback(
                    currentPacketId, CardStatus.SUCCESS, jsonWith(
                        JsonFeature.IsLenient
                    ).encodeToString(response).decryptResponse()
                )
            }

            override fun onPacketFailure(packetId: String, error: StrategyException) {
                currentPacketId = packetId
                strategyViewModel.sendCallback(currentPacketId, CardStatus.ERROR, error.getErrorString())
            }

            override fun onPacketRetry(packetId: String, attempt: Int, error: StrategyException) {
                currentPacketId = packetId
                strategyViewModel.sendCallback(currentPacketId, CardStatus.LOG, "重试第 $attempt 次, 由于 ${error.getErrorString()}")
            }

            override fun onStrategyComplete(success: Boolean) {
                strategyViewModel.sendCallback(currentUser?.userId?.content, CardStatus.NONE, "策略执行结束, 返回值 $success")
            }
        }
    }

    val slideDistance = rememberSlideDistance()

    var clearDialogVisible by remember { mutableStateOf(false) }

    if (clearDialogVisible) {
        AlertDialog(
            onDismissRequest = { clearDialogVisible = false },
            onConfirmClick = {
                clearDialogVisible = false
                strategyViewModel.clearCallbacks()
            },
            title = "清空日志",
            text = "确认要清空所有日志吗？"
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = runningStatus,
            transitionSpec = {
                materialSharedAxisXIn(
                    forward = true,
                    slideDistance = slideDistance,
                ) togetherWith materialSharedAxisXOut(
                    forward = true,
                    slideDistance = slideDistance,
                )
            }
        ) { status ->
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (status == RunningStatus.PENDING || status == RunningStatus.COMPLETE) {
                    IconButton(
                        onClick = onBack,
                        variant = IconButtonVariant.PrimaryGhost
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack)
                    }
                }

                Text(
                    text = when (status) {
                        RunningStatus.PENDING -> "等待执行"
                        RunningStatus.RUNNING -> "正在执行"
                        RunningStatus.COMPLETE -> "执行结束"
                    },
                    style = MedalTheme.typography.h1,
                )
            }
        }

        HorizontalDivider()
        Row {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(callbackList) { callback ->
                    StrategyCallbackCard(
                        callback.packetId,
                        callback.status,
                        callback.content,
                    )
                }
            }
            VerticalDivider()
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DashBoardCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Filled.Info,
                        title = "策略信息",
                        subtitle = strategy.toString()
                    )

                    DashBoardCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Filled.AccountTree,
                        title = "账号库信息",
                        subtitle = accountPath
                    )

                    DashBoardCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Filled.DataObject,
                        title = "当前运行数据包",
                        subtitle = currentPacketId?: ""
                    )

                    DashBoardCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Filled.AccountBox,
                        title = "当前运行账号",
                        subtitle = currentUser?.userId?.content?:""
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        text = "清空日志",
                        variant = ButtonVariant.Destructive,
                        enabled = runningStatus != RunningStatus.RUNNING,
                        onClick = { clearDialogVisible = true }
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        text = when(runningStatus) {
                            RunningStatus.PENDING -> "开始执行"
                            RunningStatus.RUNNING -> "正在执行"
                            RunningStatus.COMPLETE -> "已结束"
                        },
                        enabled = runningStatus == RunningStatus.PENDING,
                        loading = runningStatus == RunningStatus.RUNNING,
                        onClick = {
                            scope.launch {
                                strategyViewModel.setRunning(RunningStatus.RUNNING)
                                strategy.runWith(
                                    userPath = accountPath,
                                    channel = settings.channel,
                                    contextCallback = contextCallback,
                                    additionalCutoff = additionalCutoff,
                                    onStrategyException = { error ->
                                        strategyViewModel.sendCallback(null, CardStatus.ERROR, error.getErrorString())
                                    },
                                    onStrategyComplete = { success ->
                                        strategyViewModel.sendCallback(null, CardStatus.NONE, "账号库执行结束, 返回值 $success")
                                        strategyViewModel.setRunning(RunningStatus.COMPLETE)
                                    },
                                    onError = { error ->
                                        strategyViewModel.sendCallback(null, CardStatus.ERROR, "致命错误: ${error.message ?: "未知错误"}")
                                    },
                                    onUserChanged = { user ->
                                        currentUser = user
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StrategyCallbackCard(
    packetId: String? = null,
    status: CardStatus,
    content: String,
) {
    val containerColor = when(status) {
        CardStatus.NONE -> MedalTheme.colors.primary
        CardStatus.LOG -> MedalTheme.colors.secondary
        CardStatus.NORMAL -> MedalTheme.colors.tertiary
        CardStatus.ERROR -> MedalTheme.colors.error
        CardStatus.SUCCESS -> MedalTheme.colors.success
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor.copy(0.4f),
            contentColor = MedalTheme.colors.onBackground
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Badge(
                modifier = Modifier.padding(4.dp).padding(vertical = 4.dp),
                containerColor = containerColor
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                packetId?.let {
                    Badge(
                        modifier = Modifier.padding(vertical = 4.dp),
                        containerColor = containerColor
                    ) {
                        Text(it)
                    }
                }

                Text(content)
            }
        }
    }
}



