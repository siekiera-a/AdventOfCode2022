package pl.arkadiuszsiekiera.day3

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

data class Compartment(
    val items: List<Item>
)

data class Rucksack(
    val firstCompartment: Compartment,
    val secondCompartment: Compartment,
)

fun List<String>.convertToRucksacks() = map {
    val firstCompartmentItems = it.substring(0, it.length / 2)
    val secondCompartmentItems = it.substring(it.length / 2)
    Rucksack(
        Compartment(firstCompartmentItems.toItemList()),
        Compartment(secondCompartmentItems.toItemList())
    )
}

fun itemsInBothCompartments(rucksack: Rucksack): Set<Item> =
     rucksack.firstCompartment.items intersect rucksack.secondCompartment.items

fun main() {
    readNotEmptyLines("day3/input.txt")
        .convertToRucksacks()
        .sumOf { rucksack ->
            val items = itemsInBothCompartments(rucksack)
            items.sumOf { it.priority() }
        }
        .let { println("Sum: $it") }
}
