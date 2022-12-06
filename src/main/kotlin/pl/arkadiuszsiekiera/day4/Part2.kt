package pl.arkadiuszsiekiera.day4

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun sectionsOverlaps(pair: ElvesPair): Boolean {
    val (firstElveIds, secondElveIds) = pair
    val idsIntersection = firstElveIds intersect secondElveIds
    return idsIntersection.isNotEmpty()
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
        .count(::sectionsOverlaps)
        .let { println("Count: $it") }
}
