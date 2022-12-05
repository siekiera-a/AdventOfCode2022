package pl.arkadiuszsiekiera.utils

fun readFile(path: String) = {}::class.java.classLoader.getResource(path).readText()

fun readNotEmptyLines(path: String) = readFile(path).split("\n").filter { it.isNotBlank() }
