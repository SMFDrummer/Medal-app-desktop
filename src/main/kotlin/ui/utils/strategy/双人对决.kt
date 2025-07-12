package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 双人对决宗师() = buildStrategy {
    version = 1
    description = "双人对决上宗师领取奖励"

    V303(10859)

    packet {
        i = "V826"

        parse(
            """
            {
              "bot": "1",
              "botTimes": "1",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "win": "1"
            }
        """.trimIndent()
        )

        repeat = 88
    }

    (1..25).forEach {
        packet {
            i = "V831"

            parse(
                """
                {
                  "index": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }
}

fun 双人对决抽奖() = buildStrategy {
    version = 1
    description = "双人对决抽奖"

    V303(10859)

    V303(10861)

    (2001..2005).forEach {
        packet {
            i = "V857"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "type": "1",
                  "bot": "0",
                  "ui": "{{ui}}",
                  "wi": "1"
                }
            """.trimIndent()
            )
        }
    }

    (2006..2010).forEach {
        packet {
            i = "V857"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "type": "1",
                  "bot": "0",
                  "ui": "{{ui}}",
                  "wi": "2"
                }
            """.trimIndent()
            )
        }
    }

    (2011..2015).forEach {
        packet {
            i = "V857"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "type": "1",
                  "bot": "0",
                  "ui": "{{ui}}",
                  "wi": "3"
                }
            """.trimIndent()
            )
        }
    }

    (2016..2020).forEach {
        packet {
            i = "V857"

            parse(
                """
                {
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ti": "$it",
                  "type": "1",
                  "bot": "0",
                  "ui": "{{ui}}",
                  "wi": "4"
                }
            """.trimIndent()
            )
        }
    }

    packet {
        i = "V1100"

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
            i = "V855"

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
