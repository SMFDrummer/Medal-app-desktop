package io.github.smfdrummer.medal_app_desktop.ui.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

suspend inline fun File.atomicWriteText(content: String) {
    val tmp = toPath().resolveSibling("$nameWithoutExtension.tmp")
    withContext(Dispatchers.IO) {
        Files.writeString(tmp, content)
        Files.move(tmp, toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
    }
}