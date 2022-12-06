package pl.arkadiuszsiekiera.day5

import pl.arkadiuszsiekiera.utils.readFile
import java.util.Stack


private fun moveBoxes(count: Int, source: Stack<Box>, destination: Stack<Box>) {
    (1..count).map { source.pop() }.forEach { box -> destination.push(box) }
}

fun main() {
    convertInput(readFile("day5/input.txt"))
        .also { (stacks, arrangements) ->
            arrangements.forEach { (count, from, to) ->
                moveBoxes(count, stacks[from], stacks[to])
            }
        }.let { (stacks) ->
            val result = stacks.map { it.peek() }.joinToString(separator = "")
            println("Result = $result")
        }
}
