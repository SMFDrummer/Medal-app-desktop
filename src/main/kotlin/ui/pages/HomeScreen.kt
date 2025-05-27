package ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.Accordion
import components.components.HorizontalDivider
import components.components.Text
import components.components.card.OutlinedCard
import components.components.rememberAccordionState
import io.github.smfdrummer.enums.Channel
import org.koin.compose.viewmodel.koinViewModel
import ui.viewmodel.HomeViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import components.components.AlertDialog
import components.components.Button
import components.components.ButtonVariant
import data.AppSettings
import data.SettingsDataStore
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Medal for Desktop",
            style = MedalTheme.typography.h1,
        )
        HorizontalDivider()
        ChannelSwitchCard()
        LatestVersionAccordionCard()
    }
}

@Composable
private fun ChannelSwitchCard() {
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())
    val currentChannel = Channel.entries.firstOrNull { it.channelId == settings.channel } ?: Channel.Official
    val scope = rememberCoroutineScope()
    var showChannelDialog by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { showChannelDialog = true }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "运行环境",
                style = MedalTheme.typography.body1
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Medal 运行于：${currentChannel.channelName} 渠道（${currentChannel.channelId}）",
                        style = MedalTheme.typography.label1,
                        color = MedalTheme.colors.onSurface
                    )
                    Text(
                        text = "包名：${currentChannel.packageName}",
                        style = MedalTheme.typography.label1,
                    )
                }
            }
        }
    }

    if (showChannelDialog) {
        AlertDialog(
            onDismissRequest = { showChannelDialog = false },
            onConfirmClick = { showChannelDialog = false },
            title = "选择渠道",
            text = "请选择要切换的渠道",
            confirmButtonText = null,
            dismissButtonText = null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Channel.entries.forEach { channel ->
                    Button(
                        text = "${channel.channelName} (${channel.channelId})",
                        variant = ButtonVariant.PrimaryGhost,
                        onClick = {
                            scope.launch {
                                settingsDataStore.setChannel(channel.channelId)
                                showChannelDialog = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun LatestVersionAccordionCard() {
    val homeViewModel = koinViewModel<HomeViewModel>()
    val accordionState = rememberAccordionState()

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Accordion(
            state = accordionState,
            headerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text("最新版本号", style = MedalTheme.typography.body1)
                    Text("数据来源：离线模式（固定）", style = MedalTheme.typography.label1)
                }
            },
            bodyContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Channel.entries.forEach { channel ->
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(channel.packageName, style = MedalTheme.typography.body1)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(channel.channelName, style = MedalTheme.typography.body1)
                                Text(channel.version, style = MedalTheme.typography.body1)
                            }
                        }
                    }
                }
            }
        )
    }
}