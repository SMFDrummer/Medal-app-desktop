package io.github.smfdrummer.medal_app_desktop.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.*
import components.components.card.OutlinedCard
import components.components.snackbar.SnackbarManager
import components.components.textfield.OutlinedTextField
import components.foundation.ToggleState
import components.foundation.rememberToggleState
import data.AppSettings
import data.SettingsDataStore
import io.github.smfdrummer.enums.Channel
import io.github.smfdrummer.medal_app_desktop.ui.utils.Users
import io.github.smfdrummer.medal_app_desktop.ui.utils.getErrorString
import io.github.smfdrummer.medal_app_desktop.ui.utils.runWith
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.刷邀请码_安卓
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.刷邀请码_苹果
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.CardStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.ExperimentViewModel
import io.github.smfdrummer.network.getMD5
import io.github.smfdrummer.network.service.login
import io.github.smfdrummer.utils.json.*
import io.github.smfdrummer.utils.strategy.ContextCallback
import io.github.smfdrummer.utils.strategy.StrategyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import network.service.modifyPassword
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import ui.pages.StrategyCallbackCard
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.*

@Composable
fun ExperimentScreen() {
    val logger = koinViewModel<ExperimentViewModel>()
    val log by logger.log.collectAsState()

    val listState = rememberLazyListState()
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard

    val isAtBottom = remember {
        derivedStateOf {
            if (log.isEmpty()) true
            else {
                val layoutInfo = listState.layoutInfo
                val visibleItemsInfo = layoutInfo.visibleItemsInfo
                if (visibleItemsInfo.isEmpty()) {
                    false
                } else {
                    // 获取最后一个可见项
                    val lastItem = visibleItemsInfo.last()
                    // 获取最后一个可见项的底部位置
                    val lastItemBottom = lastItem.offset + lastItem.size
                    // 获取可视区域的总高度
                    val viewportHeight = layoutInfo.viewportSize.height
                    // 增加容差值，设置为 50dp
                    val tolerance = 50
                    // 如果最后一个可见项的底部位置接近或等于可视区域高度，则认为在底部
                    lastItemBottom >= viewportHeight - tolerance
                }
            }
        }
    }

    // 自动滚动到底部
    LaunchedEffect(log.size) {
        if (log.isNotEmpty() && isAtBottom.value) {
            listState.animateScrollToItem(log.size - 1)
        }
    }

    Row {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp).weight(3f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "实验性功能",
                style = MedalTheme.typography.h1,
            )
            HorizontalDivider()
            FunctionList()
        }
        VerticalDivider()
        Box(modifier = Modifier.weight(2f)) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Spacer(Modifier.height(16.dp)) }
                items(log) {
                    StrategyCallbackCard(
                        modifier = Modifier.clickable {
                            val selection = StringSelection(it)
                            clipboard.setContents(selection, null)
                            SnackbarManager.showSnackbar("已复制到剪贴板")
                        },
                        status = CardStatus.NONE,
                        content = it
                    )
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }

}

@Composable
private fun FunctionList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { InviteCard() }
        item { ChangePasswordCard() }
        item { ChangePasswordBatchCard() }
    }
}

@Composable
private fun LazyItemScope.ChangePasswordCard() {
    var phoneOrUserId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var newPassWord by remember { mutableStateOf("") }
    var isRandom by remember { mutableStateOf(false) }

    val logger = koinViewModel<ExperimentViewModel>()
    val scope = rememberCoroutineScope()

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .animateItem()
    ) {
        Accordion(
            headerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text("修改密码", style = MedalTheme.typography.body1)
                    Text("单一", style = MedalTheme.typography.label1)
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneOrUserId,
                    onValueChange = { phoneOrUserId = it },
                    placeholder = { Text("userId 或 手机号") },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("密码") },
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = newPassWord,
                        enabled = !isRandom,
                        onValueChange = { newPassWord = it },
                        placeholder = { Text("新密码") },
                    )
                    Switch(
                        checked = isRandom,
                        onCheckedChange = { isRandom = it },
                    )
                    Text("随机", style = MedalTheme.typography.body1)
                }
                Button(
                    Modifier.fillMaxWidth(),
                    text = "修改",
                    enabled = phoneOrUserId.isNotEmpty() && password.isNotEmpty() && (newPassWord.isNotEmpty() || isRandom),
                    onClick = {
                        scope.launch(Dispatchers.IO) {
                            val randomPassword = UUID.randomUUID().toString().split("-")[4]
                            runCatching {
                                logger.i("登录账号：$phoneOrUserId")
                                val (userId, token) = login(phoneOrUserId, password.getMD5())
                                modifyPassword(
                                    token, userId, password, when (isRandom) {
                                        true -> randomPassword
                                        false -> newPassWord
                                    }
                                )
                            }.onFailure {
                                logger.i("密码修改失败：${it.message ?: it.toString()}")
                            }.onSuccess {
                                logger.i(
                                    "密码修改成功：账号：$phoneOrUserId 密码：${
                                        when (isRandom) {
                                            true -> randomPassword
                                            false -> newPassWord
                                        }
                                    }"
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun LazyItemScope.ChangePasswordBatchCard() {
    var newPassWord by remember { mutableStateOf("") }
    var isRandom by remember { mutableStateOf(false) }

    val logger = koinViewModel<ExperimentViewModel>()
    val scope = rememberCoroutineScope()
    val state = rememberToggleState()

    AccountDialog(
        state = state,
        functionName = "修改密码（批量）"
    ) { userPath ->
        scope.launch(Dispatchers.IO) {
            val fileMutex = Mutex()

            runCatching {
                val file = File(userPath)
                val data = file.readText().fromJson<Users>(
                    JsonFeature.ImplicitNulls,
                    JsonFeature.IgnoreUnknownKeys,
                    JsonFeature.AllowTrailingComma
                )

                for (user in data.users.filter { it.activate && !it.banned && !it.password.isNullOrEmpty() }) {
                    val randomPassword = UUID.randomUUID().toString().split("-")[4]
                    runCatching {
                        logger.i("登录账号：${user.userId.content}")
                        val (userId, token) = login(user.userId.content, user.password!!.getMD5())
                        modifyPassword(
                            token, userId, user.password!!, when (isRandom) {
                                true -> randomPassword
                                false -> newPassWord
                            }
                        )
                    }.onFailure {
                        logger.i("账号：${user.userId.content} 修改密码出错：${it.message ?: it.toString()}")
                    }.onSuccess {
                        logger.i("账号：${user.userId.content} 修改密码成功")
                        user.password = randomPassword
                        user.activate = false
                    }

                    fileMutex.withLock {
                        file.writeText(
                            jsonWith(
                                JsonFeature.PrettyPrint,
                                JsonFeature.ImplicitNulls,
                                JsonFeature.IgnoreUnknownKeys
                            ).encodeToString(data)
                        )
                    }
                }
            }.onFailure {
                logger.i("批量修改密码出错：${it.message ?: it.toString()}")
            }.onSuccess {
                logger.i("批量修改密码结束")
            }
        }
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .animateItem()
    ) {
        Accordion(
            headerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text("修改密码", style = MedalTheme.typography.body1)
                    Text("批量", style = MedalTheme.typography.label1)
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = newPassWord,
                        enabled = !isRandom,
                        onValueChange = { newPassWord = it },
                        placeholder = { Text("新密码") },
                    )
                    Switch(
                        checked = isRandom,
                        onCheckedChange = { isRandom = it },
                    )
                    Text("随机", style = MedalTheme.typography.body1)
                }
                Button(
                    Modifier.fillMaxWidth(),
                    text = "执行",
                    enabled = newPassWord.isNotEmpty() || isRandom,
                    onClick = {
                        state.value = true
                    }
                )
            }
        }
    }
}

@Composable
private fun LazyItemScope.InviteCard() {
    var codes by remember { mutableStateOf("") }

    val logger = koinViewModel<ExperimentViewModel>()
    val scope = rememberCoroutineScope()
    val state = rememberToggleState()

    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    val contextCallback = object : ContextCallback {
        override fun onPacketStart(packetId: String, request: JsonObject) {
            logger.i("$packetId\n$request")
        }

        override fun onPacketSuccess(packetId: String, response: JsonObject) {
            logger.i("$packetId\n$response")
        }

        override fun onPacketFailure(
            packetId: String,
            error: StrategyException
        ) {
            logger.i("$packetId\n${error.getErrorString()}")
        }

        override fun onPacketRetry(
            packetId: String,
            attempt: Int,
            error: StrategyException
        ) {
            logger.i("$packetId($attempt)\n${error.getErrorString()}")
        }

        override fun onStrategyComplete(success: Boolean) {
            // do nothing
        }

    }

    AccountDialog(
        state = state,
        functionName = "刷邀请码 - 渠道：${Channel.entries.first { it.channelId == settings.channel }.channelName}"
    ) { path ->
        scope.launch(Dispatchers.IO) {
            codes.lineSequence().forEach { code ->
                when (settings.channel) {
                    -1 -> 刷邀请码_苹果(code)
                    else -> 刷邀请码_安卓(code)
                }.runWith(
                    userPath = path,
                    channel = settings.channel,
                    contextCallback = contextCallback,
                    additionalCutoff = { it >= 12 },
                    onUserChanged = { logger.i("更换账号：${it.userId.content}") },
                    onStrategyException = { logger.i("发生错误：${it.getErrorString()}") },
                    onStrategyComplete = { /* do nothing */ },
                    onError = { logger.i("错误：${it.message ?: it.toString()}") },
                    checkSuccess = {
                        it.responses["V876"]?.get("d")?.asObject?.getJsonArray("bl")?.contains(
                            buildJsonObject {
                                put("i", 3008)
                                put("q", 288)
                            }
                        ) ?: false
                    }
                )
            }
        }
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .animateItem()
    ) {
        Accordion(
            headerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text("刷邀请码", style = MedalTheme.typography.body1)
                    Text(
                        "请务必优先检查渠道设置是否正确，设置错误会导致无法运行，运行中途请勿修改渠道信息",
                        style = MedalTheme.typography.label1
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = codes,
                    onValueChange = { codes = it },
                    placeholder = { Text("邀请码，每行一个") },
                )
                Button(
                    Modifier.fillMaxWidth(),
                    text = "执行",
                    enabled = codes.isNotEmpty(),
                    onClick = {
                        state.value = true
                    }
                )
            }
        }
    }
}

@Composable
private fun AccountDialog(
    state: ToggleState,
    functionName: String,
    onSelected: (String) -> Unit,
) {
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    if (state.value) {
        AlertDialog(
            onDismissRequest = {
                state.value = false
            },
            onConfirmClick = {
                state.value = false
            },
            confirmButtonText = null,
            dismissButtonText = null,
            title = "选择运行账号库",
            text = "选择要运行 $functionName 功能的账号库"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (settings.accounts.isEmpty()) {
                    Text(
                        "请先在账号页面添加账号库",
                        style = MedalTheme.typography.body2,
                        color = MedalTheme.colors.error
                    )
                } else {
                    settings.accounts
                        .map { path -> File(path) }
                        .sortedByDescending { it.lastModified() }
                        .forEach { file ->
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                variant = ButtonVariant.PrimaryGhost,
                                onClick = {
                                    state.value = false
                                    onSelected(file.absolutePath)
                                }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = file.name,
                                        style = MedalTheme.typography.body1
                                    )
                                    Text(
                                        text = when {
                                            file.length() < 1024 -> "${file.length()} B"
                                            file.length() < 1024 * 1024 -> "${file.length() / 1024} KB"
                                            else -> "${file.length() / (1024 * 1024)} MB"
                                        },
                                        style = MedalTheme.typography.body2,
                                        color = MedalTheme.colors.onSurface.copy(0.6f)
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}