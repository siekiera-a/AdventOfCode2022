package pl.arkadiuszsiekiera.day14


fun main() {
    val paths = readPaths("day14/input.txt")
    val cave = widthAndHeight(paths).let { (width, height) -> Cave(width, height) }

    paths.forEach { path ->
        path.pairsOfPoints().forEach { (start, end) -> cave.addRock(start, end) }
    }

    var count = 0

    while (runCatching { cave.pourSand() }.isSuccess) {
        count++
    }

    println(count)
}