package pl.arkadiuszsiekiera.day12

import kotlin.Int.Companion.MAX_VALUE

fun main() {
    val board = readBoard("day12/input.txt")
    board.grid.entries
        .filter { (_, value) -> value.height == 0 }
        .minOfOrNull { (vertex) ->
            runCatching { findShortestRoad(board.copy(start = vertex)) }.getOrNull() ?: MAX_VALUE
        }
        .let { println(it) }
}