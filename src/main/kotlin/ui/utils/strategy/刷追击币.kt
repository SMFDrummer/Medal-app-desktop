package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 刷追击币(q: Int, count: Int) = buildStrategy {
    version = 1
    description = "刷追击币"

    packet {
        i = "V326"

        parse(
            """
            {
              "ad": "0",
              "f": "0",
              "fi": "50001",
              "l": "",
              "lv": "4",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V209"

        parse(
            """
            {
              "is": "0",
              "oi": "52304",
              "pi": "{{pi}}",
              "q": "$q",
              "si": "1",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "uk": "2"
            }
        """.trimIndent()
        )

        onFailure { false }
    }

    packet {
        i = "V927"

        parse(
            """
            {
              "fr": {
                "t": "1",
                "l": "4",
                "g": "3",
                "s": "8998",
                "r": "1",
                "b": "1.000000"
              },
              "g": "1",
              "on": "5e4bd51958f449da9dc6191e5fa1331a",
              "pi": "{{pi}}",
              "pr": "",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )

        repeat = count
    }
}