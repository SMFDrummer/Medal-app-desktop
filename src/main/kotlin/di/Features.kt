package io.github.smfdrummer.medal_app_desktop.di

import io.github.smfdrummer.medal_app_desktop.ui.utils.buildFeatures
import io.github.smfdrummer.medal_app_desktop.ui.utils.strategy.*
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
                getJsonArray("pl")?.apply {
                    find { it.asObject?.getString("i") == "200134" }?.asObject?.getString("s")
                        ?.let { user.properties["200134"] = it } // 超级机枪
                    find { it.asObject?.getString("i") == "200140" }?.asObject?.getString("s")
                        ?.let { user.properties["200140"] = it } // 海豌豆
                }

                getJsonArray("pcl")?.apply {
                    find { it.asObject?.getString("i") == "22000830" }?.asObject?.getString("q")
                        ?.let { user.properties["22000830"] = it } // 电鳗香蕉碎片
                    find { it.asObject?.getString("i") == "22000790" }?.asObject?.getString("q")
                        ?.let { user.properties["22000790"] = it } // 贪吃龙草碎片
                    find { it.asObject?.getString("i") == "22001280" }?.asObject?.getString("q")
                        ?.let { user.properties["22001280"] = it } // 守卫菇碎片
                }

                getJsonArray("il")?.apply {
                    find { it.asObject?.getString("i") == "23046" }?.asObject?.getString("q")
                        ?.let { user.properties["23046"] = it } // 进阶书
                    find { it.asObject?.getString("i") == "23225" }?.asObject?.getString("q")
                        ?.let { user.properties["23225"] = it } // 万能碎片
                    find { it.asObject?.getString("i") == "23093" }?.asObject?.getString("q")
                        ?.let { user.properties["23093"] = it } // 追击币
                    find { it.asObject?.getString("i") == "23124" }?.asObject?.getString("q")
                        ?.let { user.properties["23124"] = it } // 高级神器祝福券
                    find { it.asObject?.getString("i") == "23098" }?.asObject?.getString("q")
                        ?.let { user.properties["23098"] = it } // TODO
                }

                getJsonArray("gene")?.apply {
                    find { it.asObject?.getInt("gi") == 73069 }?.asObject?.getInt("l")
                        ?.let { user.properties["73069"] = "$it" } // 萝卜瓷砖基因
                }

                getJsonObject("p")?.apply {
                    getString("fg")?.let { user.properties["3008"] = it } // 钻石
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
        strategy { values -> 兑换物品(values["c"]!!) }
    }

    feature {
        title { "七日指南" }
        description { "七日指南一键领取" }
        strategy { 七日指南() }
    }

    feature {
        title { "刷追击币" }
        description { "购买电池数量 q，刷取次数 count" }
        inputs {
            number("q") { "购买电池数量" }
            number("count") { "刷取次数" }
        }
        strategy { values -> 刷追击币(values["q"]!!.toInt(), values["count"]!!.toInt()) }
    }

    feature {
        title { "刷通行证" }
        description { "庆典任务奖励领取与抽奖" }
        strategy { 刷通行证() }
    }

    feature {
        title { "双人对决宗师锅" }
        description { "双人对决上宗师领取奖励" }
        strategy { 双人对决宗师() }
    }

    feature {
        title { "双人对决抽奖" }
        description { "双人对决抽奖" }
        strategy { 双人对决抽奖() }
    }

    feature {
        title { "回忆之旅普通关卡" }
        description { "回忆普通关卡速通" }
        strategy { 回忆之旅普通() }
    }

    feature {
        title { "回忆之旅困难关卡" }
        description { "回忆困难关卡速通" }
        strategy { 回忆之旅困难() }
    }

    feature {
        title { "回忆之旅成就" }
        description { "回忆成就解锁" }
        strategy { 回忆之旅成就() }
    }

    feature {
        title { "回忆之旅兑换" }
        description { "兑换数量 ci，兑换物品 gi，兑换货币 mi，自定义数量 q" }
        inputs {
            number("ci") { "兑换数量" }
            number("gi") { "兑换物品" }
            number("mi") { "兑换货币" }
            number("q") { "自定义数量" }
        }
        strategy { values ->
            回忆之旅兑换(
                values["ci"]!!.toInt(),
                values["gi"]!!.toInt(),
                values["mi"]!!.toInt(),
                values["q"]!!.toInt()
            )
        }
    }

    feature {
        title { "宝藏线索" }
        description { "戴夫宝藏线索300" }
        strategy { 宝藏线索() }
    }

    feature {
        title { "植物家族重置" }
        description { "家族序号 fi" }
        inputs {
            number("fi") { "家族序号" }
        }
        strategy { values -> 植物家族重置(values["fi"]!!.toInt()) }
    }

    feature {
        title { "碎片挑战" }
        description { "碎片挑战仙人掌" }
        strategy { 碎片挑战() }
    }

    feature {
        title { "章鱼神器" }
        description { "领取章鱼神器" }
        strategy { 神器领取() }
    }

    feature {
        title { "追击传奇" }
        description { "追击传奇一键每周700钻石" }
        strategy { 追击传奇() }
    }

    feature {
        title { "追击线索" }
        description { "追击指南线索300" }
        strategy { 追击线索() }
    }

    feature {
        title { "追击兑换" }
        description { "追击指南兑换材料：粉尘 + 水晶" }
        strategy { 追击兑换() }
    }

    feature {
        title { "宝箱养号" }
        description { "宝箱养号" }
        strategy { 宝箱养号() }
    }

    feature {
        title { "夏日签到" }
        description { "夏日签到" }
        strategy { 夏日签到() }
    }

    feature {
        title { "菜问装扮" }
        description { "海螺大作战任务领取和兑换菜问超级装扮" }
        strategy { 菜问装扮() }
    }

    feature {
        title { "僵局逃脱" }
        description { "僵局逃脱" }
        strategy { 僵局逃脱() }
    }

    feature {
        title { "获取邀请码" }
        description { "获取邀请码" }
        strategy { 获取邀请码() }
        analyze { context, user ->
            context.responses["V303"]
                ?.get("d")?.asArray?.getJsonObject(0)
                ?.getString("data")?.parseObject()
                ?.getString("code")?.apply {
                    user.properties["code"] = this
                }
        }
    }

    feature {
        title { "邀请抽奖" }
        description { "当bai值为7是万能碎片×20，当bai为6是进阶书，当bai为5则是魔豆" }
        inputs {
            number("bai") { "奖励序号" }
        }
        strategy { values ->
            邀请抽奖(
                values["bai"]!!.toInt(),
            )
        }
    }

    feature {
        title { "V231自定义领取" }
        description { "oi领取物品ID" }
        inputs {
            number("oi") { "领取物品ID" }
        }
        strategy { values ->
            临时领取(
                values["oi"]!!.toInt(),
            )
        }
    }
}