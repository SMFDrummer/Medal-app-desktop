package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 宝藏线索() = buildStrategy {
    version = 1
    description = "戴夫宝藏线索300"

    packet {
        i = "V795"

        parse(
            """
            {
              "ai": "10749",
              "g": "1",
              "pi": "{{pi}}",
              "s": "300",
              "sk": "{{sk}}",
              "ti": "1002",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}