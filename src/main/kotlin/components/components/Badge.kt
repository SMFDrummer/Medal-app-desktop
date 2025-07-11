package components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirst
import components.MedalTheme
import components.components.BadgeDefaults.BadgeHorizontalOffset
import components.components.BadgeDefaults.BadgeShape
import components.components.BadgeDefaults.BadgeSize
import components.components.BadgeDefaults.BadgeVerticalOffset
import components.components.BadgeDefaults.BadgeWithContentHorizontalOffset
import components.components.BadgeDefaults.BadgeWithContentHorizontalPadding
import components.components.BadgeDefaults.BadgeWithContentSize
import components.components.BadgeDefaults.BadgeWithContentVerticalOffset
import components.components.BadgeDefaults.BadgeWithContentVerticalPadding
import components.contentColorFor
import components.foundation.ProvideContentColorTextStyle
import kotlin.math.roundToInt

@Composable
fun BadgedBox(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    var layoutAbsoluteLeft by remember { mutableFloatStateOf(0f) }
    var layoutAbsoluteTop by remember { mutableFloatStateOf(0f) }
    var greatGrandParentAbsoluteRight by remember { mutableFloatStateOf(Float.POSITIVE_INFINITY) }
    var greatGrandParentAbsoluteTop by remember { mutableFloatStateOf(Float.NEGATIVE_INFINITY) }

    Layout(
        {
            Box(
                modifier = Modifier.layoutId("anchor"),
                contentAlignment = Alignment.Center,
                content = content,
            )
            Box(
                modifier = Modifier.layoutId("badge"),
                content = badge,
            )
        },
        modifier =
            modifier
                .onGloballyPositioned { coordinates ->
                    layoutAbsoluteLeft = coordinates.boundsInWindow().left
                    layoutAbsoluteTop = coordinates.boundsInWindow().top
                    val layoutGreatGrandParent =
                        coordinates.parentLayoutCoordinates?.parentLayoutCoordinates?.parentCoordinates
                    layoutGreatGrandParent?.let {
                        greatGrandParentAbsoluteRight = it.boundsInWindow().right
                        greatGrandParentAbsoluteTop = it.boundsInWindow().top
                    }
                },
    ) { measurables, constraints ->
        val badgePlaceable =
            measurables.fastFirst { it.layoutId == "badge" }.measure(
                constraints.copy(minHeight = 0),
            )

        val anchorPlaceable = measurables.fastFirst { it.layoutId == "anchor" }.measure(constraints)

        val firstBaseline = anchorPlaceable[FirstBaseline]
        val lastBaseline = anchorPlaceable[LastBaseline]
        val totalWidth = anchorPlaceable.width
        val totalHeight = anchorPlaceable.height

        layout(
            totalWidth,
            totalHeight,
            mapOf(
                FirstBaseline to firstBaseline,
                LastBaseline to lastBaseline,
            ),
        ) {
            val hasContent = badgePlaceable.width > (BadgeSize.roundToPx())
            val badgeHorizontalOffset = if (hasContent) BadgeWithContentHorizontalOffset else BadgeHorizontalOffset
            val badgeVerticalOffset = if (hasContent) BadgeWithContentVerticalOffset else BadgeVerticalOffset

            anchorPlaceable.placeRelative(0, 0)

            var badgeX = anchorPlaceable.width + badgeHorizontalOffset.roundToPx()
            var badgeY = -badgePlaceable.height / 2 + badgeVerticalOffset.roundToPx()
            val badgeAbsoluteTop = layoutAbsoluteTop + badgeY
            val badgeAbsoluteRight = layoutAbsoluteLeft + badgeX + badgePlaceable.width.toFloat()
            val badgeGreatGrandParentHorizontalDiff =
                greatGrandParentAbsoluteRight - badgeAbsoluteRight
            val badgeGreatGrandParentVerticalDiff =
                badgeAbsoluteTop - greatGrandParentAbsoluteTop
            if (badgeGreatGrandParentHorizontalDiff < 0) {
                badgeX += badgeGreatGrandParentHorizontalDiff.roundToInt()
            }
            if (badgeGreatGrandParentVerticalDiff < 0) {
                badgeY -= badgeGreatGrandParentVerticalDiff.roundToInt()
            }

            badgePlaceable.placeRelative(badgeX, badgeY)
        }
    }
}

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable (RowScope.() -> Unit)? = null,
) {
    val size = if (content != null) BadgeWithContentSize else BadgeSize
    val shape = BadgeShape

    Row(
        modifier =
            modifier
                .defaultMinSize(minWidth = size, minHeight = size)
                .background(
                    color = containerColor,
                    shape = shape,
                )
                .clip(shape)
                .then(
                    if (content != null) {
                        Modifier.padding(
                            horizontal = BadgeWithContentHorizontalPadding,
                            vertical = BadgeWithContentVerticalPadding,
                        )
                    } else {
                        Modifier
                    },
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (content != null) {
            val style = MedalTheme.typography.label3
            ProvideContentColorTextStyle(
                contentColor = contentColor,
                textStyle = style,
                content = { content() },
            )
        }
    }
}

object BadgeDefaults {
    val containerColor: Color @Composable get() = MedalTheme.colors.error

    internal val BadgeWithContentSize = 18.dp
    internal val BadgeSize = 8.dp
    internal val BadgeShape = RoundedCornerShape(50)
    internal val BadgeWithContentHorizontalPadding = 4.dp
    internal val BadgeWithContentVerticalPadding = 2.dp

    internal val BadgeWithContentHorizontalOffset = (-6).dp
    internal val BadgeWithContentVerticalOffset = 0.dp

    internal val BadgeHorizontalOffset = (-BadgeSize / 2)
    internal val BadgeVerticalOffset = 0.dp
}
