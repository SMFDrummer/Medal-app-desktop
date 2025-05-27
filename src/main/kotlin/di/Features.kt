package io.github.smfdrummer.medal_app_desktop.di

import io.github.smfdrummer.medal_app_desktop.ui.utils.buildFeatures
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.*

val features = buildFeatures {
    feature {
        title { "创意庭院点赞" }
        description { "需要关卡 id 与点赞次数" }
        inputs {
            text("id") { "关卡ID" }
            number("count") { "点赞次数" }
        }
        strategy { values -> 庭院点赞(values["id"]!!) }
        cutoff { values -> { it >= values["count"]!!.toInt() } }
    }
}