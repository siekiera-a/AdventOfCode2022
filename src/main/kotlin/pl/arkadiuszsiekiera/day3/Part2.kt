package pl.arkadiuszsiekiera.day3

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

data class ElvesGroup(
    val first: List<Item>,
    val second: List<Item>,
    val third: List<Item>,
) {

    fun findBadge(): Item {
        val badge = first intersect second intersect third
        check(badge.size == 1) { "Invalid badges count! Count = ${ badge.size }"}
        return badge.first()
    }

}

fun main() {
    readNotEmptyLines("day3/input.txt")
        .chunked(3) { (firstElveItems, secondElveItems, thirdElveItems) ->
            ElvesGroup(
                firstElveItems.toItemList(),
                secondElveItems.toItemList(),
                thirdElveItems.toItemList(),
            )
        }
        .sumOf { group -> group.findBadge().priority() }
        .let { println("Sum = $it") }
}
