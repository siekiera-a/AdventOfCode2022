package pl.arkadiuszsiekiera.day10

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

private data class Accumulator(val value: Int) {
    fun add(addition: Int) = copy(value = value + addition)
}

private data class Sprite(val middlePoint: Int) {

    fun spritePoints() = listOf(middlePoint - 1, middlePoint, middlePoint + 1)

}

fun main() {
    val accumulatorStates = readNotEmptyLines("day10/input.txt").toCycles()
        .fold(listOf(Accumulator(1))) { accumulators, command ->
            val incrementBy = command.value ?: 0
            accumulators + accumulators.last().add(incrementBy)
        }
        .dropLast(1)

    val cursors = accumulatorStates.indices.toList()

    accumulatorStates.zip(cursors)
        .map { (accumulatorState, cursor) ->
            val sprite = Sprite(accumulatorState.value)
            val horizontalPos = cursor % 40
            if (horizontalPos in sprite.spritePoints()) "#" else "."
        }
        .chunked(40)
        .let { rows ->
            val screen = rows.joinToString(separator = "\n") { columns ->
                columns.joinToString(separator = "")
            }
            println(screen)
        }
}
