package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 选择植物(plantId: Int) = buildStrategy {
    version = 1
    description = "植物培育选择植物"

    V303(10871)

    packet {
        i = "V987"
        parse(
            """
            {"pi":"{{pi}}","plant_id":"$plantId","sk":"{{sk}}","t":"0","ui":"{{ui}}"}
        """.trimIndent()
        )
    }
}

fun 培养植物(startId: Int, endId: Int) = buildStrategy {
    version = 1
    description = "植物培育完成任务"

    V303(10871)

    (startId..endId).forEach {
        packet {
            i = "V988"
            parse(
                """
            {"pi":"{{pi}}","sk":"{{sk}}","t":"0","task_id":"$it","ui":"{{ui}}"}
        """.trimIndent()
            )
        }
    }
}

fun 领取植物() = buildStrategy {
    version = 1
    description = "植物培育领取植物"

    V303(10871)

    packet {
        i = "V989"
        parse(
            """
            {"pi":"{{pi}}","sk":"{{sk}}","t":"0","ui":"{{ui}}"}
        """.trimIndent()
        )
    }
}