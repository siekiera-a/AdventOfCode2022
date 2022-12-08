package pl.arkadiuszsiekiera.day8

typealias Forest = List<List<Int>>
typealias Tree = Int

typealias TreeVisitor = (column: Int, row: Int, forest: Forest) -> List<Tree>

val leftDirectionVisitor: TreeVisitor = { column, row, forest ->
    with(forest[row]) { subList(0, column).toList().reversed() }
}

val rightDirectionVisitor: TreeVisitor = { column, row, forest ->
    with(forest[row]) { subList(column + 1, size).toList() }
}

val topDirectionVisitor: TreeVisitor = { column, row, forest ->
    forest.subList(0, row).map { it[column] }.reversed()
}

val bottomDirectionVisitor : TreeVisitor = { column, row, forest ->
    with(forest) { subList(row + 1, size).map { it[column] } }
}

val allDirectionsVisitors = listOf(
    leftDirectionVisitor,
    rightDirectionVisitor,
    topDirectionVisitor,
    bottomDirectionVisitor
)
