package pl.arkadiuszsiekiera.day11

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun stillItems(monkey: Monkey, monkeys: List<Monkey>, worryManager: WorryManger) {
    while (true) {
        val (throwTo, item) = monkey.inspect(worryManager) ?: break
        monkeys[throwTo].catchItem(item)
    }
}

fun main() {
    val monkeys = readNotEmptyLines("day11/input.txt").chunked(6).map(::parseMonkey)

    repeat(20) {
        monkeys.forEach { monkey ->
            stillItems(monkey, monkeys) { value -> value / 3 }
        }
    }

    val (first, second) = monkeys.map { it.itemsInspected }.sortedDescending().take(2)
    println(first * second)
}
