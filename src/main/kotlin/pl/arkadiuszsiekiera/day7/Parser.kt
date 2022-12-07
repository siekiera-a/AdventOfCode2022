package pl.arkadiuszsiekiera.day7

private fun String.isCommand() = startsWith("$")

fun parseInput(text: String): List<Command> {
    val executedCommands = mutableListOf<Command>()
    var commandWithArg: String? = null
    val output = mutableListOf<String>()

    for (line in text.split("\n")) {
        if (line.isBlank()) {
            continue
        }

        if (line.isCommand()) {
            if (commandWithArg != null) {
                executedCommands.add(parseCommand(commandWithArg, output))
                output.clear()
            }
            commandWithArg = line
        } else {
            output.add(line)
        }
    }

    if (output.isNotEmpty() && commandWithArg != null) {
        executedCommands.add(parseCommand(commandWithArg, output))
    }

    return executedCommands
}

