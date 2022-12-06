package pl.arkadiuszsiekiera.day6

import pl.arkadiuszsiekiera.utils.readFile

fun main() {
    readFile("day6/input.txt").let { text ->
        val windowSize = 14
        val markIndex = text.windowed(windowSize, 1).indexOfFirst(::allCharsDifferent)
        markIndex + windowSize
    }.let { println("Result = $it") }
}
