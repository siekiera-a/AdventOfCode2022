package pl.arkadiuszsiekiera.day1

import pl.arkadiuszsiekiera.utils.readFile


fun main() {
    val elvesWithCalories = readFile("day1/input.txt")

    val caloriesOfTopThreeElves = elvesWithCalories.split("\n\n")
        .map { calories ->
            calories.split("\n")
                .filter { it.isNotBlank() }
                .sumOf { it.toInt() }
        }
        .sortedDescending()
        .take(3)
        .sum()

    println("Calories of top three elves: $caloriesOfTopThreeElves")
}
