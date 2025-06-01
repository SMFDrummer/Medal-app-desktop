package di

import data.SettingsDataStore
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.ArchiveViewModel
import io.github.smfdrummer.medal_app_desktop.ui.viewmodel.StrategyViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ui.viewmodel.HomeViewModel

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { StrategyViewModel() }
    viewModel { ArchiveViewModel() }
    single { SettingsDataStore() }
} 