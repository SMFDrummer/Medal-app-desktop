package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 刷通行证() = buildStrategy {
    version = 1
    description = "通行证任务与抽奖"

    V303(10851)

    listOf(3001, 3009, 3010, 3011, 3012, 3013, 3014, 3015, 3016, 3017, 3018).forEach {
        packet {
            i = "V432"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "bot": "0",
                  "type": "2",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }


    listOf(2001, 2006, 2004, 2005, 2007, 2008, 2009, 2010, 2015).forEach {
        packet {
            i = "V432"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "bot": "0",
                  "type": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    packet {
        i = "V1105"

        parse(
            """
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    (0..11).forEach {
        packet {
            i = "V430"

            parse(
                """
                {
                  "ln": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "type": "0",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }
}