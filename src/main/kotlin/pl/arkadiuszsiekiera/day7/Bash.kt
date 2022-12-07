package pl.arkadiuszsiekiera.day7

class Bash(
    defaultLocation: Directory = Directory(
        name = "/",
        parent = null,
        filesAndDirectories = mutableListOf()
    )
) {

    private var currentLocation = defaultLocation

    operator fun invoke(command: Command) {
        currentLocation = command(currentLocation)
    }

    fun findRoot(): Directory {
        var root = currentLocation
        while (root.parent != null) {
            root = root.parent!!
        }
        return root
    }

}
