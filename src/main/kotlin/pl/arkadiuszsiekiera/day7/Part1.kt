package pl.arkadiuszsiekiera.day7

import pl.arkadiuszsiekiera.utils.readFile

fun sumSizesOfDirectoriesWithSizeBelow(directory: Directory, limit: Long) =
    (directory.subDirectories() + directory).filter {
        it.size <= limit
    }.sumOf { it.size }

fun main() {
    val commands = parseInput(readFile("day7/input.txt"))
    val bash = Bash()

    commands.forEach { command -> bash(command) }

    val root = bash.findRoot()
    val limit = 100000L

    println("${sumSizesOfDirectoriesWithSizeBelow(root, limit)}")
}
