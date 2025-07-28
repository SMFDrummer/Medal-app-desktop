package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 临时领取(oi: Int) = buildStrategy {
    version = 1
    description = "V231自定义领取"

    packet {
        i = "V231"

        parse(
            """
            {
              "oi": "$oi",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}