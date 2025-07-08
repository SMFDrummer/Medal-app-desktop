package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 追击传奇() = buildStrategy {
    version = 1
    description = "追击传奇一键每周700钻石"

    V303(10800)

    packet {
        i = "V927"

        parse(
            """
            {
              "fr": {
                "b": "1.000000",
                "g": "3",
                "l": "1",
                "r": "1",
                "s": "35000",
                "t": "1"
              },
              "g": "1",
              "on": "a2636e2574774169bee2ef522a5ffd5c",
              "pi": "{{pi}}",
              "pr": {
                "pl": [
                  { "i": 1045, "q": 1 },
                  { "i": 1008, "q": 1 },
                  { "i": 111064, "q": 1 },
                  { "i": 111016, "q": 1 },
                  { "i": 111019, "q": 1 },
                  { "i": 200058, "q": 1 },
                  { "i": 200034, "q": 1 }
                ]
              },
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V927"

        parse(
            """
            {
              "fr": {
                "b": "1.000000",
                "g": "3",
                "l": "2",
                "r": "1",
                "s": "35000",
                "t": "1"
              },
              "g": "1",
              "on": "a2636e2574774169bee2ef522a5ffd5c",
              "pi": "{{pi}}",
              "pr": {
                "pl": [
                  { "i": 1045, "q": 1 },
                  { "i": 1008, "q": 1 },
                  { "i": 111064, "q": 1 },
                  { "i": 111016, "q": 1 },
                  { "i": 111019, "q": 1 },
                  { "i": 200058, "q": 1 },
                  { "i": 200034, "q": 1 }
                ]
              },
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V927"

        parse(
            """
            {
              "fr": {
                "b": "1.000000",
                "g": "3",
                "l": "3",
                "r": "1",
                "s": "35000",
                "t": "1"
              },
              "g": "1",
              "on": "a2636e2574774169bee2ef522a5ffd5c",
              "pi": "{{pi}}",
              "pr": {
                "pl": [
                  { "i": 1045, "q": 1 },
                  { "i": 1008, "q": 1 },
                  { "i": 111064, "q": 1 },
                  { "i": 111016, "q": 1 },
                  { "i": 111019, "q": 1 },
                  { "i": 200058, "q": 1 },
                  { "i": 200034, "q": 1 }
                ]
              },
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    packet {
        i = "V927"

        parse(
            """
            {
              "fr": {
                "b": "1.000000",
                "g": "3",
                "l": "4",
                "r": "1",
                "s": "35000",
                "t": "1"
              },
              "g": "1",
              "on": "a2636e2574774169bee2ef522a5ffd5c",
              "pi": "{{pi}}",
              "pr": {
                "pl": [
                  { "i": 1045, "q": 1 },
                  { "i": 1008, "q": 1 },
                  { "i": 111064, "q": 1 },
                  { "i": 111016, "q": 1 },
                  { "i": 111019, "q": 1 },
                  { "i": 200058, "q": 1 },
                  { "i": 200034, "q": 1 }
                ]
              },
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }

    (0..2).forEach {
        packet {
            i = "V965"

            parse(
                """
                {
                  "is": "$it",
                  "pi": "{{pi}}",
                  "sk": "{{sk}}",
                  "ui": "{{ui}}"
                }
            """.trimIndent()
            )
        }
    }
}