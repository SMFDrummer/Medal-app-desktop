package io.github.smfdrummer.medal_app_desktop.di

import io.github.smfdrummer.medal_app_desktop.ui.utils.buildFeatures
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.庭院点赞
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.获取云端存档
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.账号激活
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.领取兑换码
import io.github.smfdrummer.utils.json.*

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

    feature {
        title { "账号初始化激活" }
        description { "V206 + V316 + V900" }
        strategy { 账号激活() }
    }

    feature {
        title { "获取账号详细信息" }
        description { "电鳗香蕉碎片，贪吃龙草碎片，守卫菇碎片，进阶书碎片\n万能碎片，钻石，追击币，萝卜瓷砖基因，高级神器祝福券" }
        strategy { 获取云端存档() }
        analyze { context, user ->
            context.responses["V316"]?.get("d")?.asObject?.apply {
                getJsonArray("pcl")?.apply {
                    find { it.asObject?.getString("i") == "22000830" }?.asObject?.getString("q")
                        ?.let { user.properties["22000830"] = it }
                    find { it.asObject?.getString("i") == "22000790" }?.asObject?.getString("q")
                        ?.let { user.properties["22000790"] = it }
                    find { it.asObject?.getString("i") == "22001280" }?.asObject?.getString("q")
                        ?.let { user.properties["22001280"] = it }
                }

                getJsonArray("il")?.apply {
                    find { it.asObject?.getString("i") == "23046" }?.asObject?.getString("q")
                        ?.let { user.properties["23046"] = it }
                    find { it.asObject?.getString("i") == "23225" }?.asObject?.getString("q")
                        ?.let { user.properties["23225"] = it }
                    find { it.asObject?.getString("i") == "23093" }?.asObject?.getString("q")
                        ?.let { user.properties["23093"] = it }
                    find { it.asObject?.getString("i") == "23124" }?.asObject?.getString("q")
                        ?.let { user.properties["23124"] = it }
                    find { it.asObject?.getString("i") == "23098" }?.asObject?.getString("q")
                        ?.let { user.properties["23098"] = it }
                }

                getJsonArray("gene")?.apply {
                    find { it.asObject?.getInt("gi") == 73069 }?.asObject?.getInt("l")
                        ?.let { user.properties["73069"] = "$it" }
                }

                getJsonObject("p")?.apply {
                    getString("fg")?.let { user.properties["3008"] = it }
                }
            }
        }
    }

    feature {
        title { "领取兑换码" }
        description { "输入可用的兑换码领取道具" }
        inputs {
            text("c") { "兑换码" }
        }
        strategy { values -> 领取兑换码(values["c"]!!) }
    }
}