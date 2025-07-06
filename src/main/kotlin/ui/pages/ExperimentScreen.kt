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
import io.github.smfdrummer.medal_app_desktop.ui.utils.Users
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.CardStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.ExperimentViewModel
import io.github.smfdrummer.network.getMD5
import io.github.smfdrummer.network.service.login
import io.github.smfdrummer.utils.json.JsonFeature
import io.github.smfdrummer.utils.json.fromJson
import io.github.smfdrummer.utils.json.jsonWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import network.service.modifyPassword
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import ui.pages.StrategyCallbackCard
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File

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
        item { ChangePasswordCard() }
        item { ChangePasswordBatchCard() }
    }
}

@Composable
private fun LazyItemScope.ChangePasswordCard() {
    var phoneOrUserId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var newPassWord by remember { mutableStateOf("") }

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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newPassWord,
                    onValueChange = { newPassWord = it },
                    placeholder = { Text("新密码") },
                )
                Button(
                    Modifier.fillMaxWidth(),
                    text = "修改",
                    enabled = phoneOrUserId.isNotEmpty() && password.isNotEmpty() && newPassWord.isNotEmpty(),
                    onClick = {
                        scope.launch(Dispatchers.IO) {
                            runCatching {
                                logger.i("登录账号：$phoneOrUserId")
                                val (userId, token) = login(phoneOrUserId, password.getMD5())
                                modifyPassword(token, userId, password, newPassWord)
                            }.onFailure {
                                logger.i("密码修改失败：${it.message ?: it.toString()}")
                            }.onSuccess {
                                logger.i("密码修改成功：账号：$phoneOrUserId 密码：$newPassWord")
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
                    runCatching {
                        logger.i("登录账号：${user.userId.content}")
                        val (userId, token) = login(user.userId.content, user.password!!.getMD5())
                        modifyPassword(token, userId, user.password!!, newPassWord)
                    }.onFailure {
                        logger.i("账号：${user.userId.content} 修改密码出错：${it.message ?: it.toString()}")
                    }.onSuccess {
                        logger.i("账号：${user.userId.content} 修改密码成功")
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newPassWord,
                    onValueChange = { newPassWord = it },
                    placeholder = { Text("新密码") },
                )
                Button(
                    Modifier.fillMaxWidth(),
                    text = "执行",
                    enabled = newPassWord.isNotEmpty(),
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