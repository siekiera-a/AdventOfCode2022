package pl.arkadiuszsiekiera.day8

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun isVisible(column: Int, row: Int, forest: Forest): Boolean {
    val treeHeight = forest[row][column]
    return allDirectionsVisitors.asSequence().map { visitor ->
        visitor(column, row, forest).all { it < treeHeight }
    }.any { it }
}


fun countVisibleTrees(forest: Forest): Int {
    var count = 0

    forEachTree(forest) { row, column ->
        if (isVisible(row, column, forest))
            count++
    }

    return count
}

fun main() {
    println(
        countVisibleTrees(
            readNotEmptyLines("day8/input.txt").convertToForest()
        )
    )
}
