package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 新功能10894() = buildStrategy {
    version = 1
    description = "新功能10894"

    V303(10894)

    packet {
        i = "V1081"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "0",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V1080"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "1",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}