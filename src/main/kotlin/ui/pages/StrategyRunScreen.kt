package ui.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.*
import components.components.card.Card
import components.components.card.CardDefaults
import components.components.card.DashBoardCard
import components.components.snackbar.SnackbarManager
import data.AppSettings
import data.SettingsDataStore
import io.github.smfdrummer.medal_app_desktop.ui.utils.User
import io.github.smfdrummer.medal_app_desktop.ui.utils.getErrorString
import io.github.smfdrummer.medal_app_desktop.ui.utils.runWith
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.CardStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.RunningStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.StrategyViewModel
import io.github.smfdrummer.network.decryptResponse
import io.github.smfdrummer.utils.json.JsonFeature
import io.github.smfdrummer.utils.json.jsonWith
import io.github.smfdrummer.utils.strategy.ContextCallback
import io.github.smfdrummer.utils.strategy.StrategyConfig
import io.github.smfdrummer.utils.strategy.StrategyContext
import io.github.smfdrummer.utils.strategy.StrategyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@Composable
fun StrategyRunScreen(
    accountPath: String,
    strategy: StrategyConfig,
    onBack: () -> Unit,
    additionalCutoff: ((Int) -> Boolean)? = null,
    onContextAnalyze: ((StrategyContext, User) -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    val strategyViewModel: StrategyViewModel = koinViewModel()
    val callbackList by strategyViewModel.callbackList.collectAsState()
    val runningStatus by strategyViewModel.runningStatus.collectAsState()

    val listState = rememberLazyListState()

    var autoScrollEnabled by remember { mutableStateOf(true) }

    // 每次 logs 更新都滚到底部（仅当开启自动滚动）
    LaunchedEffect(callbackList.size, autoScrollEnabled) {
        if (autoScrollEnabled && callbackList.isNotEmpty()) {
            listState.animateScrollToItem(callbackList.lastIndex)
        }
    }

    // 设置当前策略信息
    LaunchedEffect(Unit) {
        strategyViewModel.setCurrentStrategy(accountPath, strategy, additionalCutoff, onContextAnalyze)
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

    val contextCallback = remember {
        object : ContextCallback {
            override fun onPacketStart(packetId: String, request: JsonObject) {
                currentPacketId = packetId
                strategyViewModel.sendCallback(currentPacketId, CardStatus.NORMAL, jsonWith(
                        JsonFeature.IsLenient
                    ).encodeToString(request)
                )
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
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { Spacer(Modifier.height(16.dp)) }
                    items(callbackList) { callback ->
                        StrategyCallbackCard(
                            modifier = Modifier.clickable {
                                val selection = StringSelection(callback.content)
                                clipboard.setContents(selection, null)
                                SnackbarManager.showSnackbar("已复制到剪贴板")
                            },
                            packetId = callback.packetId,
                            status = callback.status,
                            content = callback.content,
                        )
                    }
                    item { Spacer(Modifier.height(16.dp)) }
                }

                FloatingActionButton(
                    containerColor = MedalTheme.colors.primary,
                    contentColor = MedalTheme.colors.onPrimary,
                    onClick = {
                        scope.launch {
                            if (!autoScrollEnabled) {
                                // 回到底部并开启自动滚动
                                listState.animateScrollToItem(callbackList.lastIndex)
                            }
                            autoScrollEnabled = !autoScrollEnabled
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    val icon = if (autoScrollEnabled) Icons.Default.Pause else Icons.Default.PlayArrow
                    Icon(
                        icon,
                        contentDescription = if (autoScrollEnabled) "Pause auto-scroll" else "Resume auto-scroll",
                        tint = MedalTheme.colors.onPrimary
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
                            scope.launch(Dispatchers.IO) {
                                strategyViewModel.setRunning(RunningStatus.RUNNING)
                                strategy.runWith(
                                    userPath = accountPath,
                                    channel = settings.channel,
                                    contextCallback = contextCallback,
                                    additionalCutoff = additionalCutoff,
                                    onContextAnalyze = onContextAnalyze,
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
fun StrategyCallbackCard(
    modifier: Modifier = Modifier,
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
        modifier = modifier.fillMaxWidth(),
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

                Text(content, maxLines = 4)
            }
        }
    }
}



