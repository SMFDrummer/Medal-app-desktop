package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.common.platformConfig
import io.github.smfdrummer.utils.strategy.buildStrategy

fun 领取兑换码(c: String) = buildStrategy {
    version = 1
    description = "领取指定兑换码"

    packet {
        i = "V330"

        parse(
            """
            {
              "c": "$c",
              "ch": "${platformConfig.channel.packageName}",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )

        onSuccess { true }
    }
}