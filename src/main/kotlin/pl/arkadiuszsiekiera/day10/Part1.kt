package pl.arkadiuszsiekiera.day10

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

private data class Register(val value: Int, val signalStrengths: List<Int>)

fun main() {
    readNotEmptyLines("day10/input.txt").toCycles()
        .foldIndexed(Register(1, listOf())) { zeroBasedIndex, acc, command ->
            val index = zeroBasedIndex + 1
            val strengthPoints = (20..220 step 40).toSet()

            val amplification = if (index in strengthPoints) acc.value * index else null
            val incrementBy = command.value ?: 0

            acc.copy(
                value = acc.value + incrementBy,
                signalStrengths = if (amplification != null) {
                    acc.signalStrengths + amplification
                } else acc.signalStrengths
            )
        }.let {
            println(it.signalStrengths.sum())
        }
}
