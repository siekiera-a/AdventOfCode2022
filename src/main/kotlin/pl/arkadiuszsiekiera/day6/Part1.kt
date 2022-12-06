package pl.arkadiuszsiekiera.day6

import pl.arkadiuszsiekiera.utils.readFile

fun allCharsDifferent(text: String) = with(text) {
    chars().distinct().count() == text.length.toLong()
}

fun main() {
    readFile("day6/input.txt").let { text ->
        val windowSize = 4
        val markIndex = text.windowed(windowSize, 1).indexOfFirst(::allCharsDifferent)
        markIndex + windowSize
    }.let { println("Result = $it") }
}
