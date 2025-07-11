package data

import io.github.smfdrummer.enums.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.io.path.*

@Serializable
data class AppSettings(
    val darkMode: String = "auto",
    val channel: Int = 208,
    val accounts: List<String> = emptyList(),
    val cryptoType: Int = 1
)

class SettingsDataStore {
    private val settingsFile = Path(System.getProperty("user.home"), ".medal", "settings.json")
    private val json = Json { 
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    
    private val _settings = MutableStateFlow(loadSettings())
    val settings: Flow<AppSettings> = _settings.asStateFlow()

    init {
        // 确保目录存在
        settingsFile.parent.createDirectories()
    }

    private fun loadSettings(): AppSettings {
        return try {
            if (settingsFile.exists()) {
                val loadedSettings = json.decodeFromString<AppSettings>(settingsFile.readText())
                // 验证设置值
                val validDarkMode = if (loadedSettings.darkMode in listOf("auto", "true", "false")) {
                    loadedSettings.darkMode
                } else {
                    "auto"
                }

                val validChannel = if (Channel.entries.any { it.channelId == loadedSettings.channel }) {
                    loadedSettings.channel
                } else {
                    208
                }
                
                // 验证账户文件路径
                val validAccounts = loadedSettings.accounts.filter { path ->
                    try {
                        val file = Path(path)
                        file.exists() && file.isRegularFile() && file.isReadable()
                    } catch (_: Exception) {
                        false
                    }
                }

                val validCryptoType = if (loadedSettings.cryptoType in listOf(1, 2)) {
                    loadedSettings.cryptoType
                } else {
                    1
                }
                
                loadedSettings.copy(
                    darkMode = validDarkMode,
                    channel = validChannel,
                    accounts = validAccounts,
                    cryptoType = validCryptoType
                )
            } else {
                AppSettings()
            }
        } catch (e: Exception) {
            AppSettings()
        }
    }

    suspend fun updateSettings(update: (AppSettings) -> AppSettings) {
        _settings.update { currentSettings ->
            val newSettings = update(currentSettings)
            // 保存到文件
            settingsFile.writeText(json.encodeToString(AppSettings.serializer(), newSettings))
            newSettings
        }
    }

    suspend fun setDarkMode(mode: String) {
        val validMode = when (mode) {
            "auto", "true", "false" -> mode
            else -> "auto"
        }
        updateSettings { it.copy(darkMode = validMode) }
    }
    
    suspend fun setChannel(channel: Int) {
        val validChannel = if (Channel.entries.any { it.channelId == channel }) {
            channel
        } else {
            208
        }
        updateSettings { it.copy(channel = validChannel) }
    }
    
    suspend fun addAccounts(paths: List<String>) {
        updateSettings { currentSettings ->
            val validPaths = paths.filter { path ->
                try {
                    val file = Path(path)
                    file.exists() && file.isRegularFile() && file.isReadable()
                } catch (e: Exception) {
                    false
                }
            }
            currentSettings.copy(accounts = (currentSettings.accounts + validPaths).distinct())
        }
    }
    
    suspend fun removeAccount(path: String) {
        updateSettings { it.copy(accounts = it.accounts.filter { p -> p != path }) }
    }

    suspend fun setCryptoType(cryptoType: Int) {
        val validCryptoType = if (cryptoType in listOf(1, 2)) {
            cryptoType
        } else {
            1
        }
        updateSettings { it.copy(cryptoType = validCryptoType) }
    }
} 