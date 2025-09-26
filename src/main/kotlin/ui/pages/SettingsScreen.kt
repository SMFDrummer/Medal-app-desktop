package ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.LocalTextStyle
import components.MedalTheme
import components.components.*
import components.components.textfield.UnderlinedTextField
import data.AppSettings
import data.SettingsDataStore
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

@Composable
fun SettingsScreen() {
    val koin = getKoin()
    val settingsDataStore = koin.get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())
    var showDarkModeDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "设置",
            style = MedalTheme.typography.h1
        )
        HorizontalDivider()
        Surface(
            modifier = Modifier.fillMaxWidth()
                .clickable { showDarkModeDialog = true },
            color = MedalTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("主题模式")
                    Text(
                        text = when (settings.darkMode) {
                            "auto" -> "跟随系统"
                            "true" -> "深色模式"
                            "false" -> "浅色模式"
                            else -> "跟随系统"
                        }
                    )
                }
            }
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MedalTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("算法版本（暂不支持切换）")
                    Text("2")
                    Switch(
                        checked = true,
                        enabled = false,
                        onCheckedChange = {
                            // TODO()
                        }
                    )
                }
            }
        }

        var currentVersion by remember { mutableStateOf(settings.version) }
        LaunchedEffect(settings.version) {
            currentVersion = settings.version
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MedalTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("游戏版本号")
                    UnderlinedTextField(
                        value = currentVersion,
                        onValueChange = { currentVersion = it },
                        isError = !Regex("""^\d+\.\d+\.\d+$""").matches(currentVersion),
                        placeholder = { Text("x.x.x") },
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        modifier = Modifier.width(256.dp)
                    )
                    IconButton(
                        enabled = Regex("""^\d+\.\d+\.\d+$""").matches(currentVersion),
                        variant = IconButtonVariant.PrimaryGhost,
                        onClick = {
                            scope.launch {
                                settingsDataStore.setVersion(currentVersion)
                            }
                        }
                    ) {
                        Icon(Icons.Outlined.Check, "check", tint = MedalTheme.colors.primary)
                    }
                }
            }
        }
    }

    if (showDarkModeDialog) {
        val selections = listOf(
            "跟随系统" to "auto",
            "深色模式" to "true",
            "浅色模式" to "false",
        )

        AlertDialog(
            onDismissRequest = { showDarkModeDialog = false },
            onConfirmClick = { showDarkModeDialog = false },
            title = "主题模式",
            text = "选择主题模式设置",
            confirmButtonText = null,
            dismissButtonText = null
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                selections.forEach { selection ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(selection.first)
                        RadioButton(
                            selected = settings.darkMode == selection.second,
                            onClick = {
                                scope.launch {
                                    settingsDataStore.setDarkMode(selection.second)
                                    showDarkModeDialog = false
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}