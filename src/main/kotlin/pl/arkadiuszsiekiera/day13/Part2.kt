package pl.arkadiuszsiekiera.day13

import pl.arkadiuszsiekiera.day13.ElementsList.Companion.singleElement
import pl.arkadiuszsiekiera.day13.Order.RIGHT
import pl.arkadiuszsiekiera.day13.Order.UNDEFINED
import pl.arkadiuszsiekiera.day13.Order.WRONG
import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun Order.toInt() = when(this) {
    UNDEFINED -> 0
    WRONG -> 1
    RIGHT -> -1
}

private val FIRST_DIVIDER = singleElement(singleElement(SingleValue(2)))
private val SECOND_DIVIDER = singleElement(singleElement(SingleValue(6)))

fun main() {
    readNotEmptyLines("day13/input.txt")
        .map { parseLine(it) }
        .let { it + listOf(FIRST_DIVIDER, SECOND_DIVIDER) }
        .sortedWith { first, second -> first.orderOfElements(second).toInt() }
        .foldIndexed(1) { index, acc, element ->
            val multiplyBy = when {
                element == FIRST_DIVIDER || element == SECOND_DIVIDER -> index + 1
                else -> 1
            }
            acc * multiplyBy
        }
        .let { println(it) }
}