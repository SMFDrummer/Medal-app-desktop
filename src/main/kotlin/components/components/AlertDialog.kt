package components.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import components.LocalContentColor
import components.MedalTheme
import components.components.AlertDialogDefaults.ButtonsCrossAxisSpacing
import components.components.AlertDialogDefaults.ButtonsMainAxisSpacing
import components.components.AlertDialogDefaults.DialogElevation
import components.components.AlertDialogDefaults.DialogMaxWidth
import components.components.AlertDialogDefaults.DialogMinWidth
import components.components.AlertDialogDefaults.DialogPadding
import components.components.AlertDialogDefaults.DialogShape
import components.components.AlertDialogDefaults.IconPadding
import components.components.AlertDialogDefaults.TextPadding
import components.components.AlertDialogDefaults.TitlePadding
import components.foundation.ProvideContentColorTextStyle
import kotlin.math.max

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String = "OK",
    dismissButtonText: String? = "Cancel",
    icon: (@Composable () -> Unit)? = null,
    shape: Shape = DialogShape,
    containerColor: Color = MedalTheme.colors.surface,
    iconContentColor: Color = MedalTheme.colors.primary,
    titleContentColor: Color = MedalTheme.colors.primary,
    textContentColor: Color = MedalTheme.colors.primary,
    elevation: Dp = DialogElevation,
    properties: DialogProperties = DialogProperties(),
    content: @Composable (() -> Unit)? = null,
) {
    AlertDialogComponent(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(variant = ButtonVariant.Ghost, text = confirmButtonText, onClick = onConfirmClick)
        },
        modifier = Modifier,
        dismissButton =
            if (dismissButtonText != null) {
                {
                    Button(variant = ButtonVariant.Ghost, text = dismissButtonText, onClick = onDismissRequest)
                }
            } else {
                null
            },
        icon = icon,
        title = { Text(text = title) },
        text = { Text(text = text) },
        shape = shape,
        containerColor = containerColor,
        iconContentColor = iconContentColor,
        titleContentColor = titleContentColor,
        textContentColor = textContentColor,
        elevation = elevation,
        properties = properties,
        content = content,
    )
}

@Composable
fun BasicAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        val dialogPaneDescription = "dialog"
        Box(
            modifier =
                modifier
                    .sizeIn(minWidth = DialogMinWidth, maxWidth = DialogMaxWidth)
                    .then(Modifier.semantics { paneTitle = dialogPaneDescription }),
            propagateMinConstraints = true,
        ) {
            content()
        }
    }
}

@Composable
private fun AlertDialogComponent(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    containerColor: Color,
    iconContentColor: Color,
    titleContentColor: Color,
    textContentColor: Color,
    elevation: Dp,
    properties: DialogProperties,
    content: @Composable (() -> Unit)? = null,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        val dialogPaneDescription = "Dialog"

        Box(
            modifier =
                modifier
                    .sizeIn(minWidth = DialogMinWidth, maxWidth = DialogMaxWidth)
                    .then(Modifier.semantics { paneTitle = dialogPaneDescription }),
            propagateMinConstraints = true,
        ) {
            if (content != null) {
                content()
            } else {
                AlertDialogContent(
                    buttons = {
                        AlertDialogFlowRow(
                            mainAxisSpacing = ButtonsMainAxisSpacing,
                            crossAxisSpacing = ButtonsCrossAxisSpacing,
                        ) {
                            dismissButton?.invoke()
                            confirmButton()
                        }
                    },
                    icon = icon,
                    title = title,
                    text = text,
                    shape = shape,
                    containerColor = containerColor,
                    elevation = elevation,
                    buttonContentColor = iconContentColor,
                    iconContentColor = iconContentColor,
                    titleContentColor = titleContentColor,
                    textContentColor = textContentColor,
                )
            }
        }
    }
}

@Composable
internal fun AlertDialogContent(
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)?,
    title: (@Composable () -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    containerColor: Color,
    elevation: Dp,
    buttonContentColor: Color,
    iconContentColor: Color,
    titleContentColor: Color,
    textContentColor: Color,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        shadowElevation = elevation,
    ) {
        Column(modifier = Modifier.padding(DialogPadding)) {
            icon?.let {
                CompositionLocalProvider(LocalContentColor provides iconContentColor) {
                    Box(
                        Modifier
                            .padding(IconPadding)
                            .align(Alignment.CenterHorizontally),
                    ) {
                        icon()
                    }
                }
            }
            title?.let {
                ProvideContentColorTextStyle(
                    contentColor = titleContentColor,
                    textStyle = MedalTheme.typography.h3,
                ) {
                    Box(
                        Modifier
                            .padding(TitlePadding)
                            .align(
                                if (icon == null) {
                                    Alignment.Start
                                } else {
                                    Alignment.CenterHorizontally
                                },
                            ),
                    ) {
                        title()
                    }
                }
            }
            text?.let {
                val textStyle = MedalTheme.typography.body1
                ProvideContentColorTextStyle(
                    contentColor = textContentColor,
                    textStyle = textStyle,
                ) {
                    Box(
                        Modifier
                            .weight(weight = 1f, fill = false)
                            .padding(TextPadding)
                            .align(Alignment.Start),
                    ) {
                        text()
                    }
                }
            }
            Box(modifier = Modifier.align(Alignment.End)) {
                val textStyle = MedalTheme.typography.body2
                ProvideContentColorTextStyle(
                    contentColor = buttonContentColor,
                    textStyle = textStyle,
                    content = buttons,
                )
            }
        }
    }
}

@Composable
internal fun AlertDialogFlowRow(
    mainAxisSpacing: Dp,
    crossAxisSpacing: Dp,
    content: @Composable () -> Unit,
) {
    Layout(content) { measurables, constraints ->
        val sequences = mutableListOf<List<Placeable>>()
        val crossAxisSizes = mutableListOf<Int>()
        val crossAxisPositions = mutableListOf<Int>()

        var mainAxisSpace = 0
        var crossAxisSpace = 0

        val currentSequence = mutableListOf<Placeable>()
        var currentMainAxisSize = 0
        var currentCrossAxisSize = 0

        // Return whether the placeable can be added to the current sequence.
        fun canAddToCurrentSequence(placeable: Placeable) =
            currentSequence.isEmpty() || currentMainAxisSize + mainAxisSpacing.roundToPx() + placeable.width <= constraints.maxWidth

        // Store current sequence information and start a new sequence.
        fun startNewSequence() {
            if (sequences.isNotEmpty()) {
                crossAxisSpace += crossAxisSpacing.roundToPx()
            }
            // Ensures that confirming actions appear above dismissive actions.
            @Suppress("ListIterator")
            sequences.add(0, currentSequence.toList())
            crossAxisSizes += currentCrossAxisSize
            crossAxisPositions += crossAxisSpace

            crossAxisSpace += currentCrossAxisSize
            mainAxisSpace = max(mainAxisSpace, currentMainAxisSize)

            currentSequence.clear()
            currentMainAxisSize = 0
            currentCrossAxisSize = 0
        }

        measurables.fastForEach { measurable ->
            // Ask the child for its preferred size.
            val placeable = measurable.measure(constraints)

            // Start a new sequence if there is not enough space.
            if (!canAddToCurrentSequence(placeable)) startNewSequence()

            // Add the child to the current sequence.
            if (currentSequence.isNotEmpty()) {
                currentMainAxisSize += mainAxisSpacing.roundToPx()
            }
            currentSequence.add(placeable)
            currentMainAxisSize += placeable.width
            currentCrossAxisSize = max(currentCrossAxisSize, placeable.height)
        }

        if (currentSequence.isNotEmpty()) startNewSequence()

        val mainAxisLayoutSize = max(mainAxisSpace, constraints.minWidth)

        val crossAxisLayoutSize = max(crossAxisSpace, constraints.minHeight)

        val layoutWidth = mainAxisLayoutSize

        val layoutHeight = crossAxisLayoutSize

        layout(layoutWidth, layoutHeight) {
            sequences.fastForEachIndexed { i, placeables ->
                val childrenMainAxisSizes =
                    IntArray(placeables.size) { j ->
                        placeables[j].width + if (j < placeables.lastIndex) mainAxisSpacing.roundToPx() else 0
                    }
                val arrangement = Arrangement.End
                val mainAxisPositions = IntArray(childrenMainAxisSizes.size)
                with(arrangement) {
                    arrange(
                        mainAxisLayoutSize,
                        childrenMainAxisSizes,
                        layoutDirection,
                        mainAxisPositions,
                    )
                }
                placeables.fastForEachIndexed { j, placeable ->
                    placeable.place(x = mainAxisPositions[j], y = crossAxisPositions[i])
                }
            }
        }
    }
}

internal object AlertDialogDefaults {
    val DialogMinWidth = 280.dp
    val DialogMaxWidth = 560.dp

    val ButtonsMainAxisSpacing = 8.dp
    val ButtonsCrossAxisSpacing = 12.dp

    val DialogPadding = PaddingValues(all = 24.dp)
    val IconPadding = PaddingValues(bottom = 16.dp)
    val TitlePadding = PaddingValues(bottom = 16.dp)
    val TextPadding = PaddingValues(bottom = 24.dp)

    val DialogShape = RoundedCornerShape(16.dp)
    val DialogElevation = 4.dp
}

@Preview
@Composable
fun AlertDialogPreviews() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var showSimpleDialog by remember { mutableStateOf(false) }
        var showSingleButtonDialog by remember { mutableStateOf(false) }
        var showLongContentDialog by remember { mutableStateOf(false) }
        var showInputDialog by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                variant = ButtonVariant.Primary,
                text = "Show Simple Dialog",
                onClick = { showSimpleDialog = true },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                variant = ButtonVariant.Primary,
                text = "Show Single Button Dialog",
                onClick = { showSingleButtonDialog = true },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                variant = ButtonVariant.Primary,
                text = "Show Long Content Dialog",
                onClick = { showLongContentDialog = true },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                variant = ButtonVariant.Primary,
                text = "Show Input Dialog",
                onClick = { showInputDialog = true },
                modifier = Modifier.fillMaxWidth(),
            )
        }

        if (showSimpleDialog) {
            AlertDialog(
                onDismissRequest = { showSimpleDialog = false },
                onConfirmClick = { showSimpleDialog = false },
                title = "Simple Alert",
                text = "This is a basic alert dialog with default buttons",
                confirmButtonText = "OK",
                dismissButtonText = "Cancel",
                containerColor = Color.White,
                titleContentColor = Color.Black,
                textContentColor = Color.DarkGray,
            )
        }

        if (showSingleButtonDialog) {
            AlertDialog(
                onDismissRequest = { showSingleButtonDialog = false },
                onConfirmClick = { showSingleButtonDialog = false },
                title = "Information",
                text = "This alert only has a confirmation button",
                confirmButtonText = "Got it",
                dismissButtonText = null, // Removes the dismiss button
                containerColor = Color.White,
                titleContentColor = Color.Black,
                textContentColor = Color.DarkGray,
            )
        }

        if (showLongContentDialog) {
            AlertDialog(
                onDismissRequest = { showLongContentDialog = false },
                onConfirmClick = { showLongContentDialog = false },
                title = "Terms & Conditions",
                text =
                    "This is a longer content example that demonstrates how the alert dialog handles " +
                        "multiple lines of text. The content will automatically adjust to show longer " +
                        "messages while maintaining readability. This is particularly useful for " +
                        "displaying terms and conditions or detailed information to users.",
                confirmButtonText = "Accept",
                dismissButtonText = "Decline",
                containerColor = Color.White,
                titleContentColor = Color.Black,
                textContentColor = Color.DarkGray,
            )
        }
    }
}

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String? = "确定",
    dismissButtonText: String? = "取消",
    content: @Composable (() -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = Modifier
                .sizeIn(
                    minWidth = DialogMinWidth,
                    maxWidth = DialogMaxWidth * 8
                ),
            shape = DialogShape,
            color = MedalTheme.colors.surface,
            shadowElevation = DialogElevation
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DialogPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 标题
                Text(
                    text = title,
                    style = MedalTheme.typography.h2,
                    color = MedalTheme.colors.onSurface,
                )
                
                // 文本
                Text(
                    text = text,
                    style = MedalTheme.typography.body1,
                    color = MedalTheme.colors.onSurface,
                )
                
                // 自定义内容
                content?.let {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        it()
                    }
                }
                
                // 按钮
                if (dismissButtonText != null || confirmButtonText != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (dismissButtonText != null) {
                            Button(
                                text = dismissButtonText,
                                variant = ButtonVariant.PrimaryGhost,
                                onClick = onDismissRequest
                            )
                        }
                        if (confirmButtonText != null) {
                            Button(
                                text = confirmButtonText,
                                variant = ButtonVariant.PrimaryGhost,
                                onClick = onConfirmClick
                            )
                        }
                    }
                }
            }
        }
    }
}
