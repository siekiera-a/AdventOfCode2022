package pl.arkadiuszsiekiera.day14

sealed interface Element

object Air : Element {
    override fun toString() = "."
}

object Rock : Element {
    override fun toString() = "#"
}

object Sand : Element {
    override fun toString() = "o"
}