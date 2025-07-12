package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 僵局逃脱() = buildStrategy {
    version = 1
    description = "僵局逃脱"

    V303(10888)

    (1..5).forEach {
        packet {
            i = "V1030"

            parse(
                """
                {
                  "ba": {
                    "d": {
                      "plantlist": []
                    }
                  },
                  "li": "$it",
                  "lct": "472570",
                  "pi": "{{pi}}",
                  "rt": "0",
                  "sk": "{{sk}}",
                  "ss": "15000,15000",
                  "ui": "{{ui}}",
                  "wi": "1"
                }
            """.trimIndent()
            )
        }
    }

    (1..15).forEach {
        packet {
            i = "V1031"

            parse(
                """
                {
                  "id": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ui": "{{ui}}",
                  "wi": "1"
                }
            """.trimIndent()
            )
        }
    }
}