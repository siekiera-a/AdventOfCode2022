package pl.arkadiuszsiekiera.day7

class IllegalOperationException(message: String) : RuntimeException(message)

fun String.isDir() = startsWith("dir")

fun List<FileSystemElement>.includes(element: FileSystemElement) = find {
    it.name == element.name && it::class == element::class
}?.let { true } ?: false

fun MutableList<FileSystemElement>.getOrPut(element: Directory): Directory {
    if (!includes(element)) {
        add(element)
    }
    return first { it.name == element.name && it is Directory } as Directory
}

interface Command {
    operator fun invoke(executionContext: Directory): Directory
}

class Cd(private val dirName: String) : Command {

    override fun invoke(executionContext: Directory) = when (dirName) {
        "." -> executionContext

        ".." -> executionContext.parent
            ?: throw IllegalOperationException("Can not go up in / directory!")

        "/" -> Bash(executionContext).findRoot()

        else -> {
            val directoryToJump = Directory(dirName, executionContext, mutableListOf())
            executionContext.filesAndDirectories.getOrPut(directoryToJump)
        }
    }

}

class Ls(private val output: List<String>) : Command {

    override fun invoke(executionContext: Directory): Directory {
        toFileSystemList(executionContext).forEach {
            val dirView = executionContext.filesAndDirectories
            if (!dirView.includes(it)) {
                dirView.add(it)
            }
        }
        return executionContext
    }

    private fun toFileSystemList(currentContext: Directory) = output.map {
        val (firstArg, name) = it.split(" ")
        if (firstArg.isDir()) {
            Directory(name, currentContext, mutableListOf())
        } else {
            File(name, firstArg.toLong(), currentContext)
        }
    }

}

fun parseCommand(line: String, output: List<String>): Command {
    val commandWithArg = line.substring(1).trim().split(" ")
    return when (val command = commandWithArg[0].lowercase()) {
        "ls" -> Ls(output.toList())
        "cd" -> Cd(commandWithArg[1])
        else -> throw IllegalStateException("Unknown command $command!")
    }
}
