package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 特殊秘境() = buildStrategy {
    version = 1
    description = "通关特殊秘境"

    V303(10849)

    (1001..1050).forEach {
        packet {
            i = "V415"

            parse(
                """
            {
              "index": "$it",
              "levelid": "${(it - 1001) / 5}",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "world": "uncharted_zcorp"
            }
        """.trimIndent()
            )
        }
    }
}