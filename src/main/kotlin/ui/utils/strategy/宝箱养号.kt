package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 宝箱养号() = buildStrategy {
    version = 1
    description = "宝箱养号"

    V303(10889)

    packet {
        i = "V507"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "1001,1002,1003",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}