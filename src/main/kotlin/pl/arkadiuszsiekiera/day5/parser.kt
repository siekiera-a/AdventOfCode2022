package pl.arkadiuszsiekiera.day5

import java.util.Stack

typealias Box = Char

data class Arrangement(
    val count: Int,
    val fromIndex: Int,
    val toIndex: Int
)

data class StacksAndArrangements(
    val stacks: List<Stack<Box>>,
    val arrangements: List<Arrangement>
)

private val numberRegex = Regex("""\d+""")
private val boxRegex = Regex("""\[.]""")

private fun convertToArrangementsList(lines: List<String>) = lines.map {
    val (count, from, to) = numberRegex.findAll(it).map { number ->
        number.value.toInt()
    }.toList()
    Arrangement(count, from - 1, to - 1)
}

private fun convertToStacksList(lines: List<String>): List<Stack<Box>> {
    val boxesMatches = lines.flatMap { boxRegex.findAll(it).toList() }.reversed()
    val boxesOnStacks = boxesMatches.groupBy(
        keySelector = { it.range.toList().first() },
        valueTransform = { it.value[1] }
    ).toSortedMap()

    return boxesOnStacks.values.map { boxes ->
        Stack<Box>().apply {
            addAll(boxes)
        }
    }
}

fun convertInput(text: String): StacksAndArrangements {
    val linesWithStacks = mutableListOf<String>()
    val linesWithArrangements = mutableListOf<String>()

    text.split("\n").forEach { line ->
        when {
            line.contains("[") -> linesWithStacks.add(line)
            line.startsWith("move") -> linesWithArrangements.add(line)
        }
    }

    return StacksAndArrangements(
        stacks = convertToStacksList(linesWithStacks),
        arrangements = convertToArrangementsList(linesWithArrangements)
    )
}
