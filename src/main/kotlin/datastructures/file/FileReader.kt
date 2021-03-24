package datastructures.file

import java.io.File

class FileReader {
    fun read(path: String): MutableList<String> {
        val lines = mutableListOf<String>()

        File(path).useLines {
            it.forEach { line ->
                lines.add(line)
            }
        }

        return lines
    }

    fun toTokens(path: String): MutableList<String> {
        val tokens = mutableListOf<String>()

        File(path).useLines {
            it.forEach { line ->
                line.split(" ").forEach {
                    if (it != " ") {
                        tokens.add(it)
                    }
                }
            }
        }

        return tokens
    }
}