package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 传奇宝箱() = buildStrategy {
    version = 1
    description = "传奇宝箱每日"

    V303(10614)

    packet {
        i = "V333"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "v": "20190804"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V313"

        parse(
            """
            {
              "ad": "0",
              "f": "2",
              "lt": "1",
              "n": "10",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "1",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V313"

        parse(
            """
            {
              "ad": "0",
              "f": "2",
              "lt": "1",
              "n": "10",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "2",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V313"

        repeat = 2
        parse(
            """
            {
              "ad": "1",
              "f": "2",
              "lt": "1",
              "n": "1",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "3",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}