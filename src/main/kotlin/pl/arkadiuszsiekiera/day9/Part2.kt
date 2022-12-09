package pl.arkadiuszsiekiera.day9

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun main() {
    val simulation = RopeSimulator.start(Position(0, 0), 10)

    readNotEmptyLines("day9/input.txt").map { move ->
        val (direction, value) = move.split(" ")
        Shift(Direction.of(direction[0]), value.toInt())
    }
        .forEach { simulation.play(it) }

    println(simulation.tail.history.distinct().count())
}
