package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 新功能10622() = buildStrategy {
    version = 1
    description = "新功能10622"

    V303(10622)

    (1001..1006).forEach {
        packet {
            i = "V1158"

            parse(
                """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "tk": "$it",
              "ui": "{{ui}}"
            }
        """.trimIndent()
            )
        }
    }
}