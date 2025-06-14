package components.components

import androidx.annotation.IntRange
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.composables.slider.BasicRangeSlider
import com.nomanr.composables.slider.BasicSlider
import com.nomanr.composables.slider.RangeSliderState
import com.nomanr.composables.slider.SliderColors
import com.nomanr.composables.slider.SliderState
import components.MedalTheme

@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @IntRange(from = 0) steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    val state =
        remember(steps, valueRange) {
            SliderState(
                value,
                steps,
                onValueChangeFinished,
                valueRange,
            )
        }

    state.onValueChangeFinished = onValueChangeFinished
    state.onValueChange = onValueChange
    state.value = value

    Slider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = colors,
    )
}

@Composable
fun Slider(
    state: SliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    require(state.steps >= 0) { "steps should be >= 0" }

    BasicSlider(modifier = modifier, state = state, colors = colors, enabled = enabled, interactionSource = interactionSource)
}

@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val state =
        remember(steps, valueRange) {
            RangeSliderState(
                value.start,
                value.endInclusive,
                steps,
                onValueChangeFinished,
                valueRange,
            )
        }

    state.onValueChangeFinished = onValueChangeFinished
    state.onValueChange = { onValueChange(it.start..it.endInclusive) }
    state.activeRangeStart = value.start
    state.activeRangeEnd = value.endInclusive

    RangeSlider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
    )
}

@Composable
fun RangeSlider(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    require(state.steps >= 0) { "steps should be >= 0" }

    BasicRangeSlider(
        modifier = modifier,
        state = state,
        enabled = enabled,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        colors = colors,
    )
}

@Stable
object SliderDefaults {
    @Composable
    fun colors(
        thumbColor: Color = MedalTheme.colors.primary,
        activeTrackColor: Color = MedalTheme.colors.primary,
        activeTickColor: Color = MedalTheme.colors.onPrimary,
        inactiveTrackColor: Color = MedalTheme.colors.secondary,
        inactiveTickColor: Color = MedalTheme.colors.primary,
        disabledThumbColor: Color = MedalTheme.colors.disabled,
        disabledActiveTrackColor: Color = MedalTheme.colors.disabled,
        disabledActiveTickColor: Color = MedalTheme.colors.disabled,
        disabledInactiveTrackColor: Color = MedalTheme.colors.disabled,
        disabledInactiveTickColor: Color = Color.Unspecified,
    ) = SliderColors(
        thumbColor = thumbColor,
        activeTrackColor = activeTrackColor,
        activeTickColor = activeTickColor,
        inactiveTrackColor = inactiveTrackColor,
        inactiveTickColor = inactiveTickColor,
        disabledThumbColor = disabledThumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledActiveTickColor = disabledActiveTickColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledInactiveTickColor = disabledInactiveTickColor,
    )
}

@Preview
@Composable
private fun SliderPreview() {
    MedalTheme {
        Column(
            modifier =
                Modifier
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            BasicText(
                text = "Slider Components",
                style = MedalTheme.typography.h3,
            )

            Column {
                BasicText(
                    text = "Basic Slider",
                    style = MedalTheme.typography.h4,
                )
                var value by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                BasicText(
                    text = "Stepped Slider (5 steps)",
                    style = MedalTheme.typography.h4,
                )
                var value by remember { mutableFloatStateOf(0.4f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    steps = 4,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                BasicText(
                    text = "Custom Range (0-100)",
                    style = MedalTheme.typography.h4,
                )
                var value by remember { mutableFloatStateOf(30f) }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Slider(
                        value = value,
                        onValueChange = { value = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.weight(1f),
                    )
                    BasicText(
                        text = "${value.toInt()}",
                        style = MedalTheme.typography.body1,
                        modifier = Modifier.width(40.dp),
                    )
                }
            }

            Column {
                BasicText(
                    text = "Disabled States",
                    style = MedalTheme.typography.h4,
                )
                Slider(
                    value = 0.3f,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                Slider(
                    value = 0.7f,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Custom Colors",
                    style = MedalTheme.typography.h4,
                )
                var value by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    colors =
                        SliderDefaults.colors(
                            thumbColor = MedalTheme.colors.error,
                            activeTrackColor = MedalTheme.colors.error,
                            inactiveTrackColor = MedalTheme.colors.error.copy(alpha = 0.3f),
                        ),
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Interactive Slider",
                    style = MedalTheme.typography.h4,
                )
                var value by remember { mutableFloatStateOf(50f) }
                var isEditing by remember { mutableStateOf(false) }
                 BasicText(
                    text = if (isEditing) "Editing..." else "Value: ${value.toInt()}",
                    style = MedalTheme.typography.body1,
                )
                Slider(
                    value = value,
                    onValueChange = {
                        value = it
                        isEditing = true
                    },
                    valueRange = 0f..100f,
                    onValueChangeFinished = { isEditing = false },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun RangeSliderPreview() {
    MedalTheme {
        Column(
            modifier =
                Modifier
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
             BasicText(
                text = "Range Slider Components",
                style = MedalTheme.typography.h3,
            )

            Column {
                 BasicText(
                    text = "Basic Range Slider",
                    style = MedalTheme.typography.h4,
                )
                var range by remember { mutableStateOf(0.2f..0.8f) }
                RangeSlider(
                    value = range,
                    onValueChange = { range = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Stepped Range Slider (5 steps)",
                    style = MedalTheme.typography.h4,
                )
                var range by remember { mutableStateOf(0.2f..0.6f) }
                RangeSlider(
                    value = range,
                    onValueChange = { range = it },
                    steps = 4,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Custom Range (0-100)",
                    style = MedalTheme.typography.h4,
                )
                var range by remember { mutableStateOf(20f..80f) }
                Column {
                    RangeSlider(
                        value = range,
                        onValueChange = { range = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                         BasicText(
                            text = "Start: ${range.start.toInt()}",
                            style = MedalTheme.typography.body1,
                        )
                         BasicText(
                            text = "End: ${range.endInclusive.toInt()}",
                            style = MedalTheme.typography.body1,
                        )
                    }
                }
            }

            Column {
                 BasicText(
                    text = "Disabled State",
                    style = MedalTheme.typography.h4,
                )
                RangeSlider(
                    value = 0.3f..0.7f,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Custom Colors",
                    style = MedalTheme.typography.h4,
                )
                var range by remember { mutableStateOf(0.3f..0.7f) }
                RangeSlider(
                    value = range,
                    onValueChange = { range = it },
                    colors =
                        SliderDefaults.colors(
                            thumbColor = MedalTheme.colors.error,
                            activeTrackColor = MedalTheme.colors.error,
                            inactiveTrackColor = MedalTheme.colors.error.copy(alpha = 0.3f),
                        ),
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Column {
                 BasicText(
                    text = "Interactive Range Slider",
                    style = MedalTheme.typography.h4,
                )
                var range by remember { mutableStateOf(30f..70f) }
                var isEditing by remember { mutableStateOf(false) }
                 BasicText(
                    text = if (isEditing) "Editing..." else "Range: ${range.start.toInt()} - ${range.endInclusive.toInt()}",
                    style = MedalTheme.typography.body1,
                )
                RangeSlider(
                    value = range,
                    onValueChange = {
                        range = it
                        isEditing = true
                    },
                    valueRange = 0f..100f,
                    onValueChangeFinished = { isEditing = false },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
