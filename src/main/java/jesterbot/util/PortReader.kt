package jesterbot.util

import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Paths

object PortReader {
    fun readPortFromFile(s: String): Int? {
        val path = Paths.get(s)

        try {
            val lines = FileUtils.readLines(path.toFile(), "UTF-8")
            return Integer.parseInt(lines.first())
        } catch (e: Exception) {
            throw RuntimeException("Failed to read port file! Tried to find it at " + path.toAbsolutePath().toString())
        }
    }
}
