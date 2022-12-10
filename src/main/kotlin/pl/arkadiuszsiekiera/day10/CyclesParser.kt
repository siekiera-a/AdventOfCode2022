package pl.arkadiuszsiekiera.day10

data class Command(
    val command: String,
    val value: Int? = null,
)

fun List<String>.toCycles() = flatMap { line ->
    when {
        line.startsWith("noop") -> listOf(Command("noop"))
        line.startsWith("addx") -> {
            val (command, value) = line.split(" ")
            listOf(Command(command), Command(command, value.toInt()))
        }
        else -> throw RuntimeException("Could not parse: $line")
    }
}
