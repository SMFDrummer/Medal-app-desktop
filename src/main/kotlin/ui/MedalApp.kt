package ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.MedalTheme
import components.components.*
import components.components.snackbar.SnackbarHost
import components.components.snackbar.rememberSnackbarHostState
import io.github.smfdrummer.medal_app_desktop.generated.resources.Res
import io.github.smfdrummer.medal_app_desktop.generated.resources.ic_launcher
import io.github.smfdrummer.medal_app_desktop.generated.resources.ic_launcher_round
import io.github.smfdrummer.medal_app_desktop.ui.pages.ArchiveScreen
import io.github.smfdrummer.medal_app_desktop.ui.pages.ExperimentScreen
import io.github.smfdrummer.medal_app_desktop.ui.utils.User
import io.github.smfdrummer.utils.strategy.StrategyConfig
import io.github.smfdrummer.utils.strategy.StrategyContext
import org.jetbrains.compose.resources.painterResource
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance
import ui.pages.*

enum class Screen {
    HOME, FEATURES, ACCOUNT, SETTINGS, STRATEGY_RUN, ARCHIVE, EXPERIMENT
}

data class StrategyRunParams(
    val accountPath: String,
    val strategy: StrategyConfig,
    val additionalCutoff: ((Int) -> Boolean)? = null,
    val onContextAnalyze: ((StrategyContext, User) -> Unit)? = null
)

@Composable
@Preview
fun MedalApp() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var strategyRunParams by remember { mutableStateOf<StrategyRunParams?>(null) }
    val snackbarHostState = rememberSnackbarHostState()
    val slideDistance = rememberSlideDistance()

    val spots = listOf(
        Triple(Screen.HOME, "主页", Pair(Icons.Filled.Home, Icons.Outlined.Home)),
        Triple(Screen.FEATURES, "功能", Pair(Icons.Filled.Apps, Icons.Outlined.Apps)),
        Triple(Screen.ACCOUNT, "账号", Pair(Icons.Filled.Person, Icons.Outlined.Person)),
        Triple(Screen.ARCHIVE, "存档", Pair(Icons.Filled.Archive, Icons.Outlined.Archive)),
        Triple(Screen.SETTINGS, "设置", Pair(Icons.Filled.Settings, Icons.Outlined.Settings)),
        Triple(Screen.EXPERIMENT, "实验", Pair(Icons.Filled.Preview, Icons.Outlined.Preview)),
    )

    var medalAppLogoAlertDialogIsVisible by remember { mutableStateOf(false) }
    if (medalAppLogoAlertDialogIsVisible) {
        AlertDialog(
            onDismissRequest = { medalAppLogoAlertDialogIsVisible = false },
            onConfirmClick = { medalAppLogoAlertDialogIsVisible = false },
            title = "Medal for Desktop",
            text = "版本：V1.0-SNAPSHOT\n Contributed by SMFDrummer",
            icon = {
                Icon(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(Res.drawable.ic_launcher),
                    tint = Color.Unspecified
                )
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            NavigationRail(
                modifier = Modifier.fillMaxHeight(),
                containerColor = MedalTheme.colors.surface,
                header = {
                    Spacer(Modifier.size(12.dp))
                    IconButton(
                        modifier = Modifier.size(54.dp),
                        variant = IconButtonVariant.PrimaryGhost,
                        onClick = { medalAppLogoAlertDialogIsVisible = true },
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_launcher_round),
                            tint = Color.Unspecified
                        )
                    }
                }
            ) {
                spots.forEach { (screen, label, icons) ->
                    NavigationRailItem(
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                if (currentScreen == screen) icons.first else icons.second,
                                contentDescription = label
                            )
                        },
                        label = { Text(label) },
                        colors = NavigationRailItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                        ),
                        alwaysShowLabel = false,
                    )
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                AnimatedContent(
                    targetState = currentScreen,
                    transitionSpec = {
                        materialSharedAxisXIn(
                            forward = true,
                            slideDistance = slideDistance,
                        ) togetherWith materialSharedAxisXOut(
                            forward = true,
                            slideDistance = slideDistance,
                        )
                    },
                    label = "screen"
                ) { screen ->
                    when (screen) {
                        Screen.HOME -> HomeScreen()
                        Screen.FEATURES -> FeaturesScreen(
                            onNavigateToStrategyRun = { accountPath, strategy, additionalCutoff, onContextAnalyze ->
                                strategyRunParams =
                                    StrategyRunParams(accountPath, strategy, additionalCutoff, onContextAnalyze)
                                currentScreen = Screen.STRATEGY_RUN
                            }
                        )

                        Screen.ACCOUNT -> AccountScreen()
                        Screen.SETTINGS -> SettingsScreen()
                        Screen.ARCHIVE -> ArchiveScreen()
                        Screen.STRATEGY_RUN -> {
                            strategyRunParams?.let { params ->
                                StrategyRunScreen(
                                    accountPath = params.accountPath,
                                    strategy = params.strategy,
                                    onBack = {
                                        currentScreen = Screen.FEATURES
                                        strategyRunParams = null
                                    },
                                    additionalCutoff = params.additionalCutoff,
                                    onContextAnalyze = params.onContextAnalyze
                                )
                            }
                        }

                        Screen.EXPERIMENT -> ExperimentScreen()
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}







