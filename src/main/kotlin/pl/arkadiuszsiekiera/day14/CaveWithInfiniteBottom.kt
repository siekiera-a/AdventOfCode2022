package pl.arkadiuszsiekiera.day14

class CaveIsFullException: RuntimeException()

class CaveWithInfiniteBottom(width: Int, height: Int) : Cave(width, height + EXTRA_DEPTH) {

    private var minX = canvas.minOf { it.keys.min() }
    private var maxX = canvas.maxOf { it.keys.max() }

    init {
        addRock(Point(0, height + EXTRA_DEPTH - 1), Point(width, height + EXTRA_DEPTH - 1))
    }

    override fun canFall(point: Point) = when {
        (point.x < minX || point.x > maxX) && point.y < height - 1 -> true
        this[point.x, point.y] == Air -> true
        else -> false
    }

    private fun addExtraColumnWithBottom(column: Int) {
        if (column < 0)
            minX --
        else maxX++
        canvas.forEachIndexed { index, map ->
            map[column] = if (index == height - 1) Rock else Air
        }
    }

    override fun fallDownAndGetHeight(point: Point): Int {
        return when {
            point.x < minX || point.x > maxX -> {
                addExtraColumnWithBottom(point.x)
                height - 2
            }

            else -> getColumn(point.x)
                .asSequence()
                .mapIndexedNotNull { index, element ->
                    index.takeIf { it >= point.y && element != Air }?.let { it - 1 }
                }
                .first()
        }
    }

    override fun pourSand() {
        val (x, y) = SAND_SOURCE
        if (this[x, y] != Air) {
            throw CaveIsFullException()
        }
        super.pourSand()
    }

    companion object {
        private const val EXTRA_DEPTH = 2
    }
}