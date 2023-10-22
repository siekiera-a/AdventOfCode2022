package pl.arkadiuszsiekiera.day14

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

data class Point(val x: Int, val y: Int)

data class Path(val points: List<Point>) {

    fun pairsOfPoints() = points.windowed(size = 2)

    companion object {
        const val SEPARATOR = " -> "
    }
}

fun readPaths(filename: String) = readNotEmptyLines(filename)
    .map { line ->
        line.split(Path.SEPARATOR)
            .map { coordinates ->
                coordinates.split(",")
                    .let { (x, y) -> Point(x.toInt(), y.toInt()) }
            }
            .let { points -> Path(points) }
    }

fun widthAndHeight(paths: List<Path>) = paths.flatMap { it.points }
    .let { point -> point.maxOf { it.x } + 1 to point.maxOf { it.y } + 1 }