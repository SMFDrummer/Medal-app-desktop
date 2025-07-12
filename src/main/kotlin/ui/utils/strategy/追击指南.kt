package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 追击线索() = buildStrategy {
    version = 1
    description = "追击指南线索300"

    V303(10803)

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
              "ti": "1001",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}

fun 追击兑换() = buildStrategy {
    version = 1
    description = "追击指南兑换材料：粉尘 + 水晶"

    V303(10803)

    (4..5).forEach {
        packet {
            i = "V792"

            parse(
                """
            {
              "ai": "10803",
              "i": "$it",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "1",
              "ui": "{{ui}}"
            }
        """.trimIndent()
            )
        }
    }
}