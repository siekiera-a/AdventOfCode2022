package pl.arkadiuszsiekiera.day12

import pl.arkadiuszsiekiera.utils.readNotEmptyLines
import pl.arkadiuszsiekiera.day12.Grid.Companion.toGrid


class Grid(map: Map<Vertex, Value>) : LinkedHashMap<Vertex, Value>(map) {

    val vertexes = keys

    private val width = map.keys.maxOf { it.x }
    private val height = map.keys.maxOf { it.y }

    fun allNeighbours(vertex: Vertex) = setOf(
        vertex.copy(x = vertex.x + 1),
        vertex.copy(x = vertex.x - 1),
        vertex.copy(y = vertex.y + 1),
        vertex.copy(y = vertex.y - 1),
    )
        .filter { !it.isOutOfGrid() && vertex.differenceOfHeight(it) <= 1 }

    private fun Vertex.isOutOfGrid() = when {
        x < 0 || x > width -> true
        y < 0 || y > height -> true
        else -> false
    }

    private fun Vertex.differenceOfHeight(otherVertex: Vertex) = getValue(otherVertex).height - getValue(this).height

    companion object {
        fun List<Pair<Vertex, Value>>.toGrid() = Grid(toMap())
    }
}

data class Vertex(
    val x: Int,
    val y: Int
)

data class Board(
    val grid: Grid,
    val start: Vertex,
    val end: Vertex
)


data class Value(
    private val char: Char,
) {
    val height: Int
        get() = when (char) {
            START -> 0
            END -> 'z' - 'a'
            else -> char - 'a'
        }

    fun isStart() = char == START

    fun isEnd() = char == END

    companion object {
        const val START = 'S'
        const val END = 'E'
    }
}

fun readBoard(fileName: String): Board {
    val grid = readNotEmptyLines(fileName)
        .mapIndexed { y, line ->
            line.split("")
                .filter { it.isNotBlank() }
                .mapIndexed { x, string -> Vertex(x, y) to Value(string[0]) }
        }
        .flatten()
        .toGrid()

    val start = grid.entries.first { (_, vertex) -> vertex.isStart() }.key
    val end = grid.entries.first { (_, vertex) -> vertex.isEnd() }.key

    return Board(grid, start, end)
}

data class Path(
    val vertex: Vertex,
    val depth: Int
)

fun findShortestRoad(board: Board): Int {
    val (grid, start, end) = board

    val paths = ArrayDeque(listOf(Path(start, 0)))
    val vertexesToVisit = grid.vertexes.toMutableSet()
    vertexesToVisit -= start

    while (paths.isNotEmpty()) {
        val path = paths.removeFirst()

        if (path.vertex == end) {
            return path.depth
        }

        grid.allNeighbours(path.vertex)
            .filter { it in vertexesToVisit }
            .forEach {
                vertexesToVisit -= it
                paths.add(Path(it, path.depth + 1))
            }
    }
    throw IllegalStateException("There is no path from $start to $end")
}