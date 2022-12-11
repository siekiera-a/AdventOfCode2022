package pl.arkadiuszsiekiera.day11

import java.util.Queue
import java.util.ArrayDeque

fun getTextAfterColon(text: String) = text.substringAfter(": ")

fun String.numberOrOld(old: Long) = takeIf { it != "old" }?.toLong() ?: old

fun parseMonkey(lines: List<String>): Monkey {
    lateinit var startingItems: Queue<Item>
    lateinit var operation: Operation
    var testDivider: Int = -1
    var trueResultMonkey: Int = -1
    var falseResultMonkey: Int = -1

    lines.drop(1).map { it.trim() }.forEach {
        val textAfterColon = getTextAfterColon(it)
        when {
            it.startsWith("Starting items") -> {
                startingItems = ArrayDeque(
                    textAfterColon.split(", ").map { x -> x.toLong() }
                )
            }

            it.startsWith("Operation") -> {
                val (num1, operationChar, num2) = textAfterColon
                    .substringAfter("new = ")
                    .split(" ")

                operation = { old ->
                    val x = num1.numberOrOld(old)
                    val y = num2.numberOrOld(old)
                    when (operationChar) {
                        "*" -> x * y
                        "+" -> x + y
                        "-" -> x - y
                        "/" -> x / y
                        else -> throw IllegalArgumentException("Illegal char $operationChar!")
                    }
                }
            }

            it.startsWith("Test") -> {
                testDivider = textAfterColon
                    .substringAfter("divisible by ")
                    .toInt()
            }

            it.startsWith("If true") ->
                trueResultMonkey = textAfterColon
                    .substringAfter("throw to monkey ")
                    .toInt()

            it.startsWith("If false") ->
                falseResultMonkey = textAfterColon
                    .substringAfter("throw to monkey ")
                    .toInt()
        }
    }

    return Monkey(
        startingItems,
        operation,
        testDivider,
        trueResultMonkey,
        falseResultMonkey,
    )
}
