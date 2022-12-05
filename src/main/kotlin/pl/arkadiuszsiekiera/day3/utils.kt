package pl.arkadiuszsiekiera.day3

typealias Item = Char

fun Item.priority() = if (isLowerCase()) {
    code - 'a'.code + 1
} else {
    code - 'A'.code + 27
}

fun String.toItemList() = toCharArray().toList()
