package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 元宝签到() = buildStrategy {
    version = 1
    description = "元宝签到活动"

    V303(10882)

    packet {
        i = "V405"

        parse(
            """
            {
              "a": "10882",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}