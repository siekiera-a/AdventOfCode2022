package pl.arkadiuszsiekiera.day14

import kotlin.Int.Companion.MAX_VALUE

class FeltToAbyssException : RuntimeException()

open class Cave(private val width: Int, protected val height: Int) {

    protected val canvas = List(height) {
        mutableMapOf<Int, Element>().apply {
            for (i in 0..width) {
                this[i] = Air
            }
        }
    }

    operator fun get(x: Int, y: Int) = canvas[y][x]

    operator fun set(x: Int, y: Int, value: Element) {
        canvas[y][x] = value
    }

    fun addRock(start: Point, end: Point) {
        val drawHorizontal = start.y == end.y
        if (drawHorizontal) {
            (minOf(start.x, end.x)..maxOf(start.x, end.x))
                .forEach { x -> this[x, start.y] = Rock }
        } else {
            (minOf(start.y, end.y)..maxOf(start.y, end.y))
                .forEach { y -> this[start.x, y] = Rock }
        }
    }

    protected fun getColumn(column: Int) = canvas.map { it[column] }

    private fun isOutOfMap(point: Point) = when {
        point.x >= width || point.x < 0 -> true
        point.y >= height || point.y < 0 -> true
        else -> false
    }

    protected open fun canFall(point: Point) = when {
        isOutOfMap(point) -> true
        this[point.x, point.y] == Air -> true
        else -> false
    }

    protected open fun fallDownAndGetHeight(point: Point) = getColumn(point.x)
        .asSequence()
        .mapIndexedNotNull { index, element ->
            index.takeIf { it >= point.y && element != Air }?.let { it - 1 }
        }
        .first()
        .takeIf { it < height - 1 }
        ?: throw FeltToAbyssException()

    private fun fallSand(point: Point) {
        val height = fallDownAndGetHeight(point)

        val left = Point(x = point.x - 1, y = height + 1)
        val right = Point(x = point.x + 1, y = height + 1)

        when {
            canFall(left) -> fallSand(left)
            canFall(right) -> fallSand(right)
            else -> this[point.x, height] = Sand
        }
    }

    open fun pourSand() {
        fallSand(SAND_SOURCE)
    }

    override fun toString(): String {
        val startColumn = canvas.minOf {
            it.entries.indexOfFirst { (_, value) -> value != Air }
                .takeIf { index -> index >= 0 }
                ?: MAX_VALUE
        }
        return canvas.joinToString(separator = "\n") {
            it.entries
                .filter { (index) -> index >= startColumn }
                .joinToString(separator = "") { it.value.toString() }
        }
    }


    companion object {
        val SAND_SOURCE = Point(500, 0)
    }
}