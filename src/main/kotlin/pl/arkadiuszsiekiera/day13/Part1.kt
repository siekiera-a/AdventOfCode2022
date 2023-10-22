package pl.arkadiuszsiekiera.day13

import pl.arkadiuszsiekiera.day13.Order.RIGHT
import pl.arkadiuszsiekiera.utils.readFile

fun main() {
    readFile("day13/input.txt").split("\n\n")
        .mapIndexed { index, pair ->
            val (left, right) = pair.split("\n")
            val order = parseLine(left).orderOfElements(parseLine(right))
            println("$order for ${index + 1}")
            if (order == RIGHT) index + 1 else 0
        }
        .sum()
        .let { println(it) }
}
