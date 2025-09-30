package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 礼品抽奖() = buildStrategy {
    version = 1
    description = "京东礼品卡抽奖"

    V303(10898)

    (1001..1003).forEach {
        packet {
            i = "V1122"
            retry = 2
            parse(
                """
            {
              "id": "$it",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
            """.trimIndent()
            )
        }
    }

    packet {
        i = "V1121"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
            """.trimIndent()
        )

        onSuccess { true }
    }
}