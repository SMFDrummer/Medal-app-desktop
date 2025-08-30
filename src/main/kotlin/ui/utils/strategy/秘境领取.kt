package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 秘境领取() = buildStrategy {
    version = 1
    description = "秘境直接领取"

    V303(10849)

    (0..15).forEach {
        packet {
            i = "V415"
            parse(
                """
            {
              "index": "${1005 + it * 5}",
              "levelid": "$it",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "world": "uncharted_anniversary_2025"
            }
        """.trimIndent()
            )
        }
    }
}