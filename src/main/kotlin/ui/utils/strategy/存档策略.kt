package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import arrow.optics.copy
import io.github.smfdrummer.network.encryptBase64
import io.github.smfdrummer.network.encryptGzip
import io.github.smfdrummer.network.getMD5
import io.github.smfdrummer.utils.json.*
import io.github.smfdrummer.utils.strategy.buildStrategy
import kotlinx.serialization.json.JsonElement

fun 获取存档信息() = buildStrategy {
    version = 1
    description = "获取存档信息"

    packet {
        i = "V203"

        parse("""
            {
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent())

        extract {
            "p" from "pr.p"
            "s" from "pr.s"
        }

        onSuccess { true }
    }
}

fun 覆盖自定义存档(
    p: JsonElement,
    s: JsonElement
) = buildStrategy {
    version = 1
    description = "覆盖自定义存档"

    val pr = p.copy {
        path("sd.n") { it.toAsciiLiteral() }
        path("sd.lmz") { it.toScaledLiteral() }
        path("sd.rsd.wr") { it.toScaledLiteral() }
        path("sd.rsd.lr") { it.toScaledLiteral() }
    }.let { jsonWith(JsonFeature.IsLenient).encodeToString(it) }

    packet {
        i = "V206"

        parse("""
            {
              "m": "${pr.getMD5()}",
              "pi": "{{pi}}",
              "pr": "${pr.encryptGzip()}",
              "s": "${jsonWith(JsonFeature.IsLenient).encodeToString(s).encodeToByteArray().encryptBase64()}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent())

        onSuccess { true }
    }
}

fun 获取云端存档() = buildStrategy {
    version = 1
    description = "获取云端存档"

    packet {
        i = "V316"

        parse(
            """
            {
              "b": "0",
              "n": "",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onSuccess { true }
    }
}