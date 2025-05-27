package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 庭院点赞(id: String) = buildStrategy {
    version = 1
    description = "创意庭院关卡点赞"

    packet {
        i = "V722"
        r = 0
        parse("""
            {"id":"$id","pi":"{{pi}}","sk":"{{sk}}","t":"1","ui":"{{ui}}"}
        """.trimIndent())

        onSuccess { true }
    }
}
