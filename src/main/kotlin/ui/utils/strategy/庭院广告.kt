package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 庭院广告() = buildStrategy {
    version = 1
    description = "每日庭院广告"

    packet {
        i = "V765"
        repeat = 5
        parse(
            """
            {
              "id": "1",
              "is": "0",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V766"

        parse(
            """
            {
              "id": "1",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}