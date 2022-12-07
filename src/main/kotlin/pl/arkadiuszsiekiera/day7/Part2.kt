package pl.arkadiuszsiekiera.day7

import pl.arkadiuszsiekiera.utils.readFile


fun findSmallestDirectoryThatEnsureEnoughSpace(root: Directory): Directory {
    val availableSpace = 70_000_000L
    val requiredSpace = 30_000_000L

    val freeSpace = availableSpace - root.size
    val spaceToCleanUp = requiredSpace - freeSpace

    check(spaceToCleanUp > 0)

    val nestedDirectories = root.subDirectories()
    return nestedDirectories.filter { it.size >= spaceToCleanUp }
        .sortedBy { it.size }
        .minBy { it.size }
}

fun main() {
    val commands = parseInput(readFile("day7/input.txt"))
    val bash = Bash()

    commands.forEach { command -> bash(command) }

    val root = bash.findRoot()

    println("${findSmallestDirectoryThatEnsureEnoughSpace(root).size}")
}
