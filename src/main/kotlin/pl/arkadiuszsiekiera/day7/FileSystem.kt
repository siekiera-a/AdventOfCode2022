package pl.arkadiuszsiekiera.day7

fun getFullPath(element: FileSystemElement): String {
    val fullPath = mutableListOf<String>()
    var current: FileSystemElement? = element
    while (current?.parent != null) {
        fullPath.add(current.name)
        current = current.parent
    }
    return fullPath.reversed().joinToString(separator = "/", prefix = "/")
}

interface FileSystemElement {
    val name: String
    val size: Long
    val parent: Directory?
}

data class Directory(
    override val name: String,
    override val parent: Directory?,
    val filesAndDirectories: MutableList<FileSystemElement>
) : FileSystemElement {

    override val size: Long
        get() = run {
            val (nestedDirectories, files) = filesAndDirectories.partition {
                it is Directory
            }
            val nestedDirectoriesSize = (nestedDirectories as List<Directory>).sumOf {
                it.size
            }
            val filesSize = files.sumOf { it.size }
            filesSize + nestedDirectoriesSize
        }

    override fun toString() = getFullPath(this)

    fun subDirectories(): List<Directory> {
        val subdirectories = filesAndDirectories.filterIsInstance<Directory>()
        return subdirectories + subdirectories.flatMap {
            it.subDirectories()
        }
    }

}

data class File(
    override val name: String,
    override val size: Long,
    override val parent: Directory,
) : FileSystemElement {

    override fun toString() = getFullPath(this)

}
