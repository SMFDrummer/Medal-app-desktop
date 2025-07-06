package components.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class ToggleState internal constructor(
    val state: MutableState<Boolean>
) {
    var value: Boolean
        get() = state.value
        set(value) {
            state.value = value
        }

    fun toggle() {
        value = !value
    }
}

@Composable
fun rememberToggleState(initial: Boolean = false): ToggleState {
    val state = remember { mutableStateOf(initial) }
    return remember(state) { ToggleState(state) }
}