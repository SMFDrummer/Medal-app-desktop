package ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.AlertDialog
import components.components.Icon
import components.components.Surface
import components.components.Text
import components.components.card.OutlinedCard
import data.AppSettings
import data.SettingsDataStore
import kotlinx.coroutines.launch
import org.koin.compose.getKoin
import soup.compose.material.motion.animation.rememberSlideDistance
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

enum class SortField {
    NAME, MODIFIED_TIME, SIZE
}

@Composable
fun AccountScreen() {
    val settingsDataStore = getKoin().get<SettingsDataStore>()
    val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())
    val scope = rememberCoroutineScope()
    val slideDistance = rememberSlideDistance()

    var sortField by remember { mutableStateOf(SortField.MODIFIED_TIME) }
    var isAscending by remember { mutableStateOf(false) }
    var selectedPath by remember { mutableStateOf<String?>(null) }

    val sortedAccounts = remember(settings.accounts, sortField, isAscending) {
        settings.accounts.map { path -> File(path) }
            .sortedWith(compareBy<File> { file ->
                when (sortField) {
                    SortField.NAME -> file.name.lowercase()
                    SortField.MODIFIED_TIME -> file.lastModified()
                    SortField.SIZE -> file.length()
                }
            }.let { if (isAscending) it else it.reversed() })
            .map { it.absolutePath }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (settings.accounts.isEmpty()) {
            EmptyState()
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .clickable {
                                if (sortField == SortField.NAME) {
                                    isAscending = !isAscending
                                } else {
                                    sortField = SortField.NAME
                                    isAscending = false
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp).padding(start = 24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                "文件名",
                                style = MedalTheme.typography.label1,
                                color = if (sortField == SortField.NAME) MedalTheme.colors.primary else MedalTheme.colors.onSurface
                            )
                            if (sortField == SortField.NAME) {
                                Icon(
                                    if (isAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    tint = MedalTheme.colors.primary
                                )
                            }
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .clickable {
                                if (sortField == SortField.MODIFIED_TIME) {
                                    isAscending = !isAscending
                                } else {
                                    sortField = SortField.MODIFIED_TIME
                                    isAscending = false
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp).padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                "最后修改时间",
                                style = MedalTheme.typography.label1,
                                color = if (sortField == SortField.MODIFIED_TIME) MedalTheme.colors.primary else MedalTheme.colors.onSurface
                            )
                            if (sortField == SortField.MODIFIED_TIME) {
                                Icon(
                                    if (isAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    tint = MedalTheme.colors.primary
                                )
                            }
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .clickable {
                                if (sortField == SortField.SIZE) {
                                    isAscending = !isAscending
                                } else {
                                    sortField = SortField.SIZE
                                    isAscending = false
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp).padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                "大小",
                                style = MedalTheme.typography.label1,
                                color = if (sortField == SortField.SIZE) MedalTheme.colors.primary else MedalTheme.colors.onSurface
                            )
                            if (sortField == SortField.SIZE) {
                                Icon(
                                    if (isAscending) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    tint = MedalTheme.colors.primary
                                )
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sortedAccounts) { path ->
                        AccountFileItem(
                            modifier = Modifier.animateItem(),
                            path = path,
                            onClick = { selectedPath = path }
                        )
                    }
                }
            }
        }

        selectedPath?.let { path ->
            val file = File(path)
            AlertDialog(
                onDismissRequest = { selectedPath = null },
                onConfirmClick = {
                    scope.launch {
                        settingsDataStore.removeAccount(path)
                        selectedPath = null
                    }
                },
                title = "移除账号库",
                text = "确定要移除 ${file.name} 吗？",
                confirmButtonText = "移除",
                dismissButtonText = "取消",
            )
        }

        FloatingActionButton(
            onClick = {
                val fileChooser = JFileChooser().apply {
                    isMultiSelectionEnabled = true
                }

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    val selectedFiles = fileChooser.selectedFiles.map { it.absolutePath }
                    scope.launch {
                        settingsDataStore.addAccounts(selectedFiles)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MedalTheme.colors.primary,
            contentColor = MedalTheme.colors.onPrimary
        ) {
            Icon(Icons.Default.Add, tint = MedalTheme.colors.onPrimary)
        }
    }
}

@Composable
private fun AccountFileItem(
    modifier: Modifier = Modifier,
    path: String,
    onClick: () -> Unit
) {
    val file = remember(path) { File(path) }
    val lastModified = remember(file) {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .format(Date(file.lastModified()))
    }
    val fileSize = remember(file) {
        when {
            file.length() < 1024 -> "${file.length()} B"
            file.length() < 1024 * 1024 -> "${file.length() / 1024} KB"
            else -> "${file.length() / (1024 * 1024)} MB"
        }
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = file.name,
                style = MedalTheme.typography.label1,
                modifier = Modifier.weight(1.9f)
            )

            Text(
                text = lastModified,
                style = MedalTheme.typography.label1,
                modifier = Modifier.weight(2f)
            )

            Text(
                text = fileSize,
                style = MedalTheme.typography.label1,
                modifier = Modifier.weight(1.6f)
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "¯\\_(ツ)_/¯",
            style = MedalTheme.typography.h2,
            color = MedalTheme.colors.onBackground.copy(0.6f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "空空如也",
            style = MedalTheme.typography.body1,
            color = MedalTheme.colors.onBackground.copy(0.6f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "点击右下角的 + 按钮添加账号库",
            style = MedalTheme.typography.label1,
            color = MedalTheme.colors.onBackground.copy(0.4f)
        )
    }
}