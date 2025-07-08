package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 追击线索() = buildStrategy {
    version = 1
    description = "追击指南线索300"

    packet {
        i = "V795"

        parse(
            """
            {
              "ai": "10803",
              "g": "1",
              "pi": "{{pi}}",
              "s": "300",
              "sk": "{{sk}}",
              "ti": "1003",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}