package pl.arkadiuszsiekiera.day8

fun List<String>.convertToForest() = map { line ->
    line.map { it.digitToInt() }
}

inline fun forEachTree(forest: Forest, action: (row: Int, column: Int) -> Unit) {
    val rows = forest.indices
    val columns = forest[0].indices

    for (row in rows) {
        for (column in columns) {
            action(row, column)
        }
    }
}
