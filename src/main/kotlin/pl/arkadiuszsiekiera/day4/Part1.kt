package pl.arkadiuszsiekiera.day4

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun onElveIsDoingNothing(pair: ElvesPair): Boolean {
    val (firstElveIds, secondElveIds) = pair
    val idsIntersection = firstElveIds intersect secondElveIds
    return firstElveIds.size == idsIntersection.size || secondElveIds.size == idsIntersection.size
}

fun main() {
    readNotEmptyLines("day4/input.txt")
        .map {
            val (firstElveIds, secondElveIds) = it.split(",")
            ElvesPair(
                convertToIdsList(firstElveIds),
                convertToIdsList(secondElveIds)
            )
        }
        .count(::onElveIsDoingNothing)
        .let { println("Count: $it") }
}
