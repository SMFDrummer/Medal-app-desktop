package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 夏日签到() = buildStrategy {
    version = 1
    description = "夏日签到"

    V303(10882)

    packet {
        i = "V405"

        parse(
            """
            {
              "a": "10882",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}

fun 菜问装扮() = buildStrategy {
    version = 1
    description = "海螺大作战任务领取和兑换菜问超级装扮"

    V303(10882)

    (1001..1018).forEach {
        packet {
            i = "V406"

            parse(
                """
           {
             "pi": "{{pi}}",
             "sk": "{{sk}}",
             "t": "$it",
             "ui": "{{ui}}"
           } 
        """.trimIndent()
            )
        }
    }

    packet {
        i = "V407"

        parse(
            """
           {
             "key": "0",
             "pi": "{{pi}}",
             "sk": "{{sk}}",
             "ui": "{{ui}}"
           }
        """.trimIndent()
        )
    }
}