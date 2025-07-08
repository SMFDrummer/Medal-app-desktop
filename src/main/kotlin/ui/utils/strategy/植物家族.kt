package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 植物家族重置(fi: Int) = buildStrategy {
    version = 1
    description = "植物家族重置"

    packet {
        i = "V326"

        parse(
            """
            {
              "ad": "0",
              "f": "0",
              "fi": "$fi",
              "l": "",
              "lv": "4",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}