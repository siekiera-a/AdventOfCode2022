package pl.arkadiuszsiekiera.day2

import pl.arkadiuszsiekiera.day2.Outcome.Victory
import pl.arkadiuszsiekiera.day2.Outcome.Draw
import pl.arkadiuszsiekiera.day2.Outcome.Lose

enum class Outcome { Victory, Draw, Lose }

sealed class Choice(val value: Int) {
    abstract fun battle(opponentChoice: Choice): Outcome
    abstract fun drawWith(): Choice
    abstract fun winWith(): Choice
    abstract fun loseWith(): Choice
}

data object Rock : Choice(1) {
    override fun battle(opponentChoice: Choice): Outcome {
        return when (opponentChoice) {
            is Paper -> Lose
            is Scissors -> Victory
            is Rock -> Draw
        }
    }

    override fun drawWith() = this
    override fun winWith() = Scissors
    override fun loseWith() = Paper
}

data object Paper : Choice(2) {
    override fun battle(opponentChoice: Choice): Outcome {
        return when (opponentChoice) {
            is Paper -> Draw
            is Scissors -> Lose
            is Rock -> Victory
        }
    }

    override fun drawWith() = this
    override fun winWith() = Rock
    override fun loseWith() = Scissors
}

data object Scissors : Choice(3) {
    override fun battle(opponentChoice: Choice): Outcome {
        return when (opponentChoice) {
            is Paper -> Victory
            is Scissors -> Draw
            is Rock -> Lose
        }
    }

    override fun drawWith() = this
    override fun winWith() = Paper
    override fun loseWith() = Rock
}
