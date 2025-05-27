package ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.*
import components.components.card.OutlinedCard
import components.components.textfield.OutlinedTextField
import data.AppSettings
import data.SettingsDataStore
import io.github.smfdrummer.medal_app_desktop.di.features
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.RunningStatus
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.StrategyViewModel
import io.github.smfdrummer.utils.strategy.StrategyConfig
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import java.io.File

@Composable
fun FeaturesScreen(
    onNavigateToStrategyRun: (String, StrategyConfig, ((Int) -> Boolean)?) -> Unit
) {
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())

    val strategyViewModel = koinViewModel<StrategyViewModel>()
    val runningStatus by strategyViewModel.runningStatus.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedStrategy by remember { mutableStateOf<StrategyConfig?>(null) }
    var additionalCutoff by remember { mutableStateOf<((Int) -> Boolean)?>(null) }
    var accountDialogVisible by remember { mutableStateOf(false) }
    var inputValues by remember { mutableStateOf(mapOf<String, String>()) }

    val filteredFeatures = remember(searchQuery) {
        features.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
            it.description.contains(searchQuery, ignoreCase = true)
        }
    }

    val accordionState = rememberAccordionGroupState(count = features.size, allowMultipleOpen = false)

    if (accountDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                accountDialogVisible = false
                selectedStrategy = null
                additionalCutoff = null
                inputValues = mapOf()
            },
            onConfirmClick = {
                accountDialogVisible = false
                selectedStrategy = null
                additionalCutoff = null
                inputValues = mapOf()
            },
            confirmButtonText = null,
            dismissButtonText = null,
            title = "选择运行账号库",
            text = "选择要运行${selectedStrategy?.toString()}策略的账号库"
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
                                accountDialogVisible = false
                                onNavigateToStrategyRun(file.absolutePath, selectedStrategy!!, additionalCutoff)
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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "功能列表",
                    style = MedalTheme.typography.h1
                )
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    leadingIcon = { Icon(Icons.Default.Search) },
                    placeholder = { Text("搜索功能...") },
                    singleLine = true,
                    modifier = Modifier.width(300.dp)
                )
            }

            HorizontalDivider()

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { 
                    if (runningStatus == RunningStatus.RUNNING) {
                        val currentAccountPath by strategyViewModel.currentAccountPath.collectAsState()
                        val currentStrategy by strategyViewModel.currentStrategy.collectAsState()
                        val currentCutoff by strategyViewModel.currentCutoff.collectAsState()
                        
                        if (currentAccountPath != null && currentStrategy != null) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    onNavigateToStrategyRun(
                                        currentAccountPath!!,
                                        currentStrategy!!,
                                        currentCutoff
                                    )
                                }
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = "有策略正在运行",
                                        style = MedalTheme.typography.h2,
                                        color = MedalTheme.colors.primary
                                    )
                                    Text(
                                        text = "点击返回策略运行页面",
                                        style = MedalTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }
                }
                itemsIndexed(filteredFeatures) { index, feature ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem()
                    ) {
                        Accordion(
                            state = accordionState.getState(index),
                            headerContent = {
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                                ) {
                                    Text(feature.title, style = MedalTheme.typography.body1)
                                    Text(feature.description, style = MedalTheme.typography.label1)
                                }
                            },
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                ) {
                                    feature.inputs.forEach { input ->
                                        OutlinedTextField(
                                            modifier = Modifier.weight(1f),
                                            value = inputValues[input.key] ?: "",
                                            onValueChange = { 
                                                inputValues = inputValues + (input.key to it)
                                            },
                                            label = { Text(input.label) },
                                            isError = input.isRequired && !input.validator(
                                                inputValues[input.key] ?: ""
                                            )
                                        )
                                    }
                                }

                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = when(runningStatus) {
                                        RunningStatus.RUNNING -> "有未完成的策略正在执行"
                                        else -> "执行"
                                    },
                                    enabled = feature.inputs.all { input ->
                                        !input.isRequired || input.validator(inputValues[input.key] ?: "")
                                    } && runningStatus != RunningStatus.RUNNING,
                                    onClick = {
                                        selectedStrategy = feature.strategyBuilder(inputValues)
                                        additionalCutoff = feature.cutoffBuilder?.invoke(inputValues)
                                        strategyViewModel.setRunning(RunningStatus.PENDING)
                                        accountDialogVisible = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}