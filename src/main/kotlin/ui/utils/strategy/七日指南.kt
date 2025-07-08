package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 七日指南() = buildStrategy {
    version = 1
    description = "七日指南一键领取"

    V303(10828)

    packet {
        i = "V950"

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

    (1001..1007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (2001..2007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (3001..3007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (4001..4007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (5001..5007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (6001..6007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (7001..7007).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "1",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }

    (0..6).forEach {
        packet {
            i = "V951"

            parse(
                """
                {
                  "ai": "10828",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "t": "2",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }
}