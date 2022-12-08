package pl.arkadiuszsiekiera.day8

import pl.arkadiuszsiekiera.utils.readNotEmptyLines

fun calculateScore(column: Int, row: Int, forest: Forest, visitor: TreeVisitor): Int {
    val treeHeight = forest[row][column]
    var score = 0
    for (currentTreeHeight in visitor(column, row, forest)) {
        score++
        if (currentTreeHeight >= treeHeight) {
            break
        }
    }
    return score
}

fun calculateHighestScenicScore(forest: Forest): Int {
    val treeScores = mutableListOf<Int>()

    forEachTree(forest) { row, column ->
        allDirectionsVisitors.map { visitor ->
            calculateScore(column, row, forest, visitor)
        }
        .let { scores -> scores.fold(1) { acc, i -> acc * i } }
        .also { treeScores.add(it) }
    }

    return treeScores.max()
}

fun main() {
    println(
        calculateHighestScenicScore(
            readNotEmptyLines("day8/input.txt").convertToForest()
        )
    )
}
