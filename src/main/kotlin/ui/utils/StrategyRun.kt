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
    val credential: Credential? = null,
    var activate: Boolean = true,
    var banned: Boolean = false,
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
    userPath: String,
    channel: Int,
    contextCallback: ContextCallback,
    additionalCutoff: ((Int) -> Boolean)? = null,
    onUserChanged: (User) -> Unit,
    onStrategyException: (StrategyException) -> Unit,
    onStrategyComplete: (Boolean) -> Unit,
    onError: (Throwable) -> Unit
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
        runCatching {
            executeWith(
                userProvider = when (channel) {
                    -1 -> IOSProvider(user.userId.content)
                    else -> OfficialProvider(user.userId.content, user.password!!.getMD5())
                },
                context = StrategyContext(contextCallback).apply {
                    user.credential?.let {
                        variables["pi"] = primitive { it.personalId }
                        variables["sk"] = primitive { it.securityKey }
                        variables["ui"] = primitive { it.userId }
                    }
                }
            ).fold(
                { error ->
                    when (error) {
                        is StrategyException.UnexpectedResponseCode -> {
                            if (error.actual == 20507) {
                                user.banned = true
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
                            onStrategyException(error)
                        }

                        else -> onStrategyException(error)
                    }
                },
                { success ->
                    user.activate = false
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
            )
        }.onFailure { error ->
            when (error) {
                is IllegalStateException -> {
                    // 处理密码缺失或格式错误
                    user.password = null
                    user.activate = false
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

                else -> onError(error)
            }
        }.onSuccess {
            successCounter.incrementAndGet()
        }
    }
}.onFailure { error ->
    onError(error)
    onStrategyComplete(false)
}.onSuccess { success ->
    onStrategyComplete(true)
}