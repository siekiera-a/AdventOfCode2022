package pl.arkadiuszsiekiera.day2

import pl.arkadiuszsiekiera.utils.readFile
import pl.arkadiuszsiekiera.day2.Outcome.Victory
import pl.arkadiuszsiekiera.day2.Outcome.Draw
import pl.arkadiuszsiekiera.day2.Outcome.Lose
import java.lang.IllegalStateException

private fun List<String>.toMovesList() = map {
    val (opponentMoveString, yourMoveString) = it.split(" ")
    val opponentMove = when (opponentMoveString) {
        "A" -> Rock
        "B" -> Paper
        "C" -> Scissors
        else -> throw IllegalStateException("Unknown opponent choice: $it")
    }
    val yourMove = when (yourMoveString) {
        // I should draw
        "Y" -> opponentMove.drawWith()
        // I should lose
        "X" -> opponentMove.winWith()
        // I should win
        "Z" -> opponentMove.loseWith()
        else -> throw IllegalStateException("Unknown your choice: $it")
    }
    listOf(opponentMove, yourMove)
}

fun main() {
    readFile("day2/input.txt")
        .split("\n")
        .filter { it.isNotBlank() }
        .toMovesList()
        .sumOf { (opponentMove, yourMove) ->
            val resultPoints = when (yourMove.battle(opponentMove)) {
                Victory -> 6
                Draw -> 3
                Lose -> 0
            }
            resultPoints + yourMove.value
        }
        .let { println("Your score: $it") }
}
