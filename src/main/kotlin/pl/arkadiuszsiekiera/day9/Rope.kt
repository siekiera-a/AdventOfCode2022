package pl.arkadiuszsiekiera.day9

import kotlin.math.abs
import pl.arkadiuszsiekiera.day9.Direction.Up
import pl.arkadiuszsiekiera.day9.Direction.Down
import pl.arkadiuszsiekiera.day9.Direction.Left
import pl.arkadiuszsiekiera.day9.Direction.Right
import kotlin.math.sign

interface Knot {
    fun currentPosition(): Position
}

data class Position(val x: Int, val y: Int) {

    fun touch(other: Position): Boolean {
        val (otherX, otherY) = other
        val xDistance = abs(x - otherX)
        val yDistance = abs(y - otherY)
        return xDistance <= 1 && yDistance <= 1
    }

}

enum class Direction(private val shortcut: Char) {
    Up('U'), Down('D'), Right('R'), Left('L');

    companion object {
        fun of(char: Char): Direction {
            return enumValues<Direction>().find {
                it.shortcut == char
            } ?: throw IllegalArgumentException("Unknown char: $char!")
        }
    }
}

class Head(startPosition: Position) : Knot {

    private val history = mutableListOf(startPosition)
    private var position = startPosition

    override fun currentPosition() = position

    fun move(direction: Direction) {
        val (x, y) = position
        val newPosition = when (direction) {
            Up -> position.copy(y = y + 1)
            Down -> position.copy(y = y - 1)
            Left -> position.copy(x = x - 1)
            Right -> position.copy(x = x + 1)
        }
        history.add(newPosition)
        position = newPosition
    }
}

fun sign(i: Int) = sign(i.toDouble()).toInt()

fun Int.normalizeShift() = if (abs(this) > 1) sign(this) else this

class RopeElement(startPosition: Position) : Knot {
    val history = mutableListOf(startPosition)
    private var position = startPosition

    override fun currentPosition() = position

    fun follow(head: Knot) {
        if (!position.touch(head.currentPosition())) {
            val newPosition = calcNewPosition(head.currentPosition())
            history.add(newPosition)
            position = newPosition
        }
    }

    private fun calcNewPosition(headPosition: Position): Position {
        val (headX, headY) = headPosition
        val (x, y) = position

        val xDistance = headX - x
        val yDistance = headY - y

        val xShift = xDistance.normalizeShift()
        val yShift = yDistance.normalizeShift()

        return Position(x = x + xShift, y = y + yShift)
    }
}

data class Shift(
    val direction: Direction,
    val value: Int,
)

class RopeSimulator private constructor(
    private val head: Head,
    private val otherKnots: List<RopeElement>
) {

    val tail = otherKnots.last()

    fun play(shift: Shift) {
        repeat(shift.value) {
            head.move(shift.direction)
            otherKnots.fold(head as Knot) { lastKnot, knot ->
                knot.apply { follow(lastKnot) }
            }
        }
    }

    companion object {
        fun start(startPosition: Position, length: Int = 2): RopeSimulator {
            require(length >= 2)
            return RopeSimulator(
                Head(startPosition),
                List(length - 1) { RopeElement(startPosition) }
            )
        }
    }

}
