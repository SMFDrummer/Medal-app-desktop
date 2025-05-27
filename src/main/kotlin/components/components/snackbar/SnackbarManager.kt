package components.components.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object SnackbarManager {
    private var hostState: SnackbarHostState? = null

    fun setHostState(state: SnackbarHostState) {
        hostState = state
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
    ) {
        hostState?.let { state ->
            GlobalScope.launch(Dispatchers.IO) {
                state.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    withDismissAction = withDismissAction,
                    duration = duration
                )
            }
        }
    }
}

@Composable
fun rememberSnackbarHostState(): SnackbarHostState {
    val state = remember { SnackbarHostState() }
    
    // 设置全局 SnackbarHostState
    SnackbarManager.setHostState(state)
    
    return state
} 