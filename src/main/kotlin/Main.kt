package io.github.smfdrummer.medal_app_desktop

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.MedalTheme
import components.components.Surface
import data.AppSettings
import data.SettingsDataStore
import di.appModule
import io.github.smfdrummer.common.platformManager
import io.github.smfdrummer.enums.Channel
import org.koin.compose.KoinApplication
import org.koin.compose.getKoin
import ui.MedalApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Medal for Desktop",
    ) {
        KoinApplication(application = { modules(appModule) }) {
            val settingsDataStore = getKoin().get<SettingsDataStore>()
            val settings by settingsDataStore.settings.collectAsState(initial = AppSettings())
            val isDarkTheme = when (settings.darkMode) {
                "true" -> true
                "false" -> false
                else -> isSystemInDarkTheme()
            }
            
            // 设置渠道
            LaunchedEffect(settings.channel) {
                if (settings.channel == -1) {
                    platformManager.switchToIOS()
                } else {
                    val channel = Channel.entries.firstOrNull { it.channelId == settings.channel } ?: Channel.Official
                    platformManager.switchToAndroid(channel)
                }
            }
            
            MedalTheme(
                isDarkTheme = isDarkTheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MedalTheme.colors.background
                ) {
                    MedalApp()
                }
            }
        }
    }
}

