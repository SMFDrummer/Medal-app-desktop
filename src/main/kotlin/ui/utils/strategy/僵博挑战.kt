package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.common.platformConfig
import io.github.smfdrummer.utils.strategy.buildStrategy

fun 僵博挑战() = buildStrategy {
    version = 1
    description = "挑战僵博"

    packet {
        i = "V303"
        retry = 2

        parse(
            """
        {
          "al": [
            {
              "id": 10613,
              "abi": 0,
              "type": 1,
              "config_version": 1
            },
            {
              "id": 10799,
              "abi": 0,
              "type": 1,
              "config_version": 1
            }
          ],
          "ci": "93",
          "cs": "0",
          "pack": "${platformConfig.channel.packageName}",
          "pi": "{{pi}}",
          "sk": "{{sk}}",
          "ui": "{{ui}}",
          "v": "${platformConfig.channel.version}"
        }
    """.trimIndent()
        )

        onFailure { false }
    }

    packet {
        i = "V273"
        repeat = 3
        parse(
            """
            {
              "bi": "0",
              "d": [
                {
                  "id": 0,
                  "k": 1,
                  "l": 0.0,
                  "t": 71.601692
                }
              ],
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V273"
        r = 21503
        repeat = 12
        parse(
            """
            {
              "bi": "0",
              "d": [
                {
                  "id": 0,
                  "k": 1,
                  "l": 0.0,
                  "t": 71.601692
                }
              ],
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}

fun 僵博抽奖() = buildStrategy {
    version = 1
    description = "挑战僵博消耗蓝色水晶单抽"

    packet {
        i = "V347"
        repeat = 230
        parse(
            """
            {
              "lct": "1",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "t": "10799",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}