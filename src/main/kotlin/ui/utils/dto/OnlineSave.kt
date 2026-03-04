package ui.utils.dto


import io.github.smfdrummer.medal_app_desktop.ui.utils.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnlineSave(
    @SerialName("pl") val pl: List<Pl> = listOf(),
    @SerialName("pcl") val pcl: List<Pcl> = listOf(),
    @SerialName("il") val il: List<Il> = listOf(),
    @SerialName("gene") val gene: List<Gene> = listOf(),
    @SerialName("p") val p: P = P()
) {
    @Serializable
    data class Pl(
        @SerialName("i") val i: String = "", // 200134
        @SerialName("s") val s: String = "" // xxx
    )

    @Serializable
    data class Pcl(
        @SerialName("i") val i: String = "", // 22000830
        @SerialName("q") val q: String = "" // xxx
    )

    @Serializable
    data class Il(
        @SerialName("i") val i: String = "", // 23046
        @SerialName("q") val q: String = "" // xxx
    )

    @Serializable
    data class Gene(
        @SerialName("gi") val gi: Int = 0, // 73069
        @SerialName("l") val l: Int = 0 // 10
    )

    @Serializable
    data class P(
        @SerialName("fg") val fg: String = "" // 1000
    )
}

fun OnlineSave.setProperties(user: User) {
    user.properties["钻石"] = p.fg
    gene.associateBy { it.gi }.apply {
        user.properties["萝卜瓷砖基因"] = this[73069]?.l.toString()
    }
    il.associateBy { it.i }.apply {
        user.properties["进阶书"] = this["23046"]?.q.toString()
        user.properties["万能碎片"] = this["23225"]?.q.toString()
        user.properties["追击币"] = this["23093"]?.q.toString()
        user.properties["高级神器祝福券"] = this["23124"]?.q.toString()
        user.properties["密保券"] = this["23098"]?.q.toString()
    }
    pcl.associateBy { it.i }.apply {
        user.properties["电鳗香蕉碎片"] = this["22000830"]?.q.toString()
        user.properties["贪吃龙草碎片"] = this["22000790"]?.q.toString()
        user.properties["守卫菇碎片"] = this["22001280"]?.q.toString()
        user.properties["超级机枪射手碎片"] = this["22001340"]?.q.toString()
        user.properties["球果训练家碎片"] = this["22001430"]?.q.toString()
        user.properties["太极木槿碎片"] = this["22001590"]?.q.toString()
        user.properties["毁灭菇碎片"] = this["22001610"]?.q.toString()
        user.properties["伏僵塔黄碎片"] = this["22001330"]?.q.toString()
    }
    pl.associateBy { it.i }.apply {
        user.properties["超级机枪射手"] = this["200134"]?.s.toString()
        user.properties["海豌豆"] = this["200140"]?.s.toString()
    }
}