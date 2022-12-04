package pl.arkadiuszsiekiera.day1

import pl.arkadiuszsiekiera.utils.readFile


fun main() {
    val elvesWithCalories = readFile("day1/input.txt")

    val maxCalories = elvesWithCalories.split("\n\n")
        .maxOf { calories ->
            calories.split("\n")
                .filter { it.isNotBlank() }
                .sumOf { it.toInt() }
        }

    println("Max calories: $maxCalories")
}
