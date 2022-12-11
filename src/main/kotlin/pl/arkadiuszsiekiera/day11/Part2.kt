package pl.arkadiuszsiekiera.day11

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun main() {
    val monkeys = readNotEmptyLines("day11/input.txt").chunked(6).map(::parseMonkey)

    val divider = monkeys.map { it.testDivider }.reduce(Int::times)

    repeat(10_000) {
        monkeys.forEach { monkey ->
            stillItems(monkey, monkeys) { value -> value % divider }
        }
    }

    val (first, second) = monkeys.map { it.itemsInspected }.sortedDescending().take(2)
    println(first.toLong() * second.toLong())
}
