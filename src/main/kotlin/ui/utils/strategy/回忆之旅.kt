package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 回忆之旅普通() = buildStrategy {
    version = 1
    description = "回忆普通关卡速通"

    V303(10836)

    (0..9).forEach {
        packet {
            i = "V971"

            parse(
                """
                {
                  "gl": "$it",
                  "r": "0",
                  "tgt": "0,1,2",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "tp": "1",
                  "ui": "{{ui}}",
                  "wi": "1"
                }
            """.trimIndent()
            )
        }
    }
}

fun 回忆之旅困难() = buildStrategy {
    version = 1
    description = "回忆困难关卡速通"

    V303(10836)

    (0..16).forEach {
        packet {
            i = "V971"

            parse(
                """
                {
                  "gl": "$it",
                  "r": "2",
                  "tgt": "0,1,2",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "tp": "2",
                  "ui": "{{ui}}",
                  "wi": "1"
                }
            """.trimIndent()
            )
        }
    }
}

fun 回忆之旅成就() = buildStrategy {
    version = 1
    description = "回忆成就解锁"

    V303(10836)

    (1001..1012).forEach {
        packet {
            i = "V976"

            parse(
                """
                {
                  "ctp": "1",
                  "i": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "tp": "10839",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }
}

fun 回忆之旅兑换(ci: Int, gi: Int, mi: Int, q: Int) = buildStrategy {
    version = 1
    description = "回忆兑换神器"

    packet {
        i = "V392"

        parse(
            """
            {
              "ci": "$ci",
              "gi": "$gi",
              "mi": "$mi",
              "pi": "{{pi}}",
              "q": "$q",
              "si": "10",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "w": ""
            }
        """.trimIndent()
        )
    }
}