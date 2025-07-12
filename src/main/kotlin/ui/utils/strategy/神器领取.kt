package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 神器领取() = buildStrategy {
    version = 1
    description = "领取章鱼神器 + 治愈神器 + 投罐神器"

    packet {
        i = "V900"

        parse(
            """
            {
              "pi": "{{pi}}",
              "pl": [
                {
                  "i": 60003,
                  "q": 1
                },
                {
                  "i": 60004,
                  "q": 1
                },
                {
                  "i": 60006,
                  "q": 1
                }
              ],
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onFailure { false }
    }
}