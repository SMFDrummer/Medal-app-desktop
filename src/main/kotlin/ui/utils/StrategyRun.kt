package io.github.smfdrummer.medal_app_desktop.ui.utils

import arrow.atomic.AtomicInt
import io.github.smfdrummer.network.getMD5
import io.github.smfdrummer.network.provider.IOSProvider
import io.github.smfdrummer.network.provider.OfficialProvider
import io.github.smfdrummer.utils.json.JsonFeature
import io.github.smfdrummer.utils.json.fromJson
import io.github.smfdrummer.utils.json.jsonWith
import io.github.smfdrummer.utils.json.primitive
import io.github.smfdrummer.utils.strategy.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

@Serializable
data class Users(@SerialName("Users") val users: List<User>)

@Serializable
data class User(
    @Required val userId: JsonPrimitive,
    val userNick: String? = null,
    val phone: String? = null,
    var password: String? = null,
    val token: String? = null,
    var credential: Credential? = null,
    var activate: Boolean = true,
    var banned: Boolean = false,
    val properties: MutableMap<String, String> = mutableMapOf()
) {
    @Serializable
    data class Credential(
        @SerialName("pi") val personalId: String,
        @SerialName("sk") val securityKey: String,
        @SerialName("ui") val userId: String,
    )
}

private val fileMutex = Mutex()

suspend fun StrategyConfig.runWith(
    channel: Int,
    phoneOrUserId: String? = null,
    password: String? = null,
    context: StrategyContext = StrategyContext()
) = runCatching {
    executeWith(
        userProvider = when (channel) {
            -1 -> if (!phoneOrUserId.isNullOrEmpty())
                IOSProvider(phoneOrUserId) else null
            else -> if (!phoneOrUserId.isNullOrEmpty() && !password.isNullOrEmpty())
                OfficialProvider(phoneOrUserId, password.getMD5()) else null
        },
        context = context
    )
}

suspend fun StrategyConfig.runWith(
    userPath: String,
    channel: Int,
    contextCallback: ContextCallback,
    additionalCutoff: ((Int) -> Boolean)? = null,
    onUserChanged: (User) -> Unit,
    onStrategyException: (StrategyException) -> Unit,
    onStrategyComplete: (Boolean) -> Unit,
    onError: (Throwable) -> Unit,
    onContextAnalyze: ((StrategyContext, User) -> Unit)? = null
) = runCatching {
    val successCounter = AtomicInt()
    val file = File(userPath)
    val data = file.readText().fromJson<Users>(
        JsonFeature.ImplicitNulls,
        JsonFeature.IgnoreUnknownKeys,
        JsonFeature.AllowTrailingComma
    )

    for (user in data.users.filter { it.activate && !it.banned }) {
        if (additionalCutoff?.invoke(successCounter.get()) == true) { break }
        onUserChanged(user)
        val context = StrategyContext(contextCallback).apply {
            user.credential?.let {
                variables["pi"] = primitive { it.personalId }
                variables["sk"] = primitive { it.securityKey }
                variables["ui"] = primitive { it.userId }
            }
        }
        runCatching {
            executeWith(
                userProvider = when (channel) {
                    -1 -> if (user.userId.content.isNotEmpty())
                        IOSProvider(user.userId.content) else null
                    else -> if (user.userId.content.isNotEmpty() && !user.password.isNullOrEmpty())
                        OfficialProvider(user.userId.content, user.password!!.getMD5()) else null
                },
                context = context
            ).fold(
                { error ->
                    when (error) {
                        is StrategyException.UnexpectedResponseCode -> {
                            if (error.actual == 20507) {
                                user.banned = true
                            }
                            onStrategyException(error)
                        }

                        else -> onStrategyException(error)
                    }
                    error(error)
                },
                { success ->
                    user.activate = false
                }
            )
        }.onFailure { error ->
            when (error) {
                is IllegalStateException -> {
                    if (error.message?.contains("密码错误") == true) user.password = null
                    user.activate = false
                }

                else -> onError(error)
            }
        }.onSuccess {
            successCounter.incrementAndGet()
        }
        with(context.variables) {
            // forEach { (k, v) -> println("$k: $v") }
            if (containsKey("pi") && containsKey("sk") && containsKey("ui")) {
                user.credential = User.Credential(
                    personalId = get("pi")!!.jsonPrimitive.content,
                    securityKey = get("sk")!!.jsonPrimitive.content,
                    userId = get("ui")!!.jsonPrimitive.content
                )
            }
        }

        onContextAnalyze?.invoke(context, user)
        
        fileMutex.withLock {
            file.writeText(
                jsonWith(
                    JsonFeature.PrettyPrint,
                    JsonFeature.ImplicitNulls,
                    JsonFeature.IgnoreUnknownKeys
                ).encodeToString(data)
            )
        }
    }
}.onFailure { error ->
    onError(error)
    onStrategyComplete(false)
}.onSuccess { success ->
    onStrategyComplete(true)
}

fun StrategyException.getErrorString() = when(this) {
    is StrategyException.UnknownRetryError -> "未知重试错误"
    is StrategyException.TemplateRenderError -> "模板渲染错误: $key"
    is StrategyException.InvalidActionValue -> "无效的操作值: $value"
    is StrategyException.NetworkError -> "网络错误: ${cause.message}"
    is StrategyException.DecryptionError -> "解密错误: ${cause.message}"
    is StrategyException.UnexpectedResponseCode -> "意外的响应码: 期望 $expect, 实际 $actual"
    is StrategyException.CredentialExpired -> "凭证已过期: 错误码 $code"
    is StrategyException.CredentialRefreshError -> "凭证刷新错误: ${cause.message}"
}