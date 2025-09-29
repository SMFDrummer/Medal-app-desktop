package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 领取红包() = buildStrategy {
    version = 1
    description = "红包幸运奖"

    V303(10898)

    packet {
        i = "V1120"

        parse(
            """
            {
              "type": "0",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}