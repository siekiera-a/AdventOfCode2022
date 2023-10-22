package pl.arkadiuszsiekiera.day13

private const val START_LIST_CHAR = '['
private const val END_LIST_CHAR = ']'

sealed interface Element

class ListBuilder(val parentBuilder: ListBuilder?) {
    private val elements = mutableListOf<Element>()
    private val nestedListBuilders = ArrayDeque<ListBuilder>()

    fun add(element: Element) {
        elements.add(element)
    }

    fun addNewNestedList() = ListBuilder(this).also { nestedListBuilders.add(it) }

    fun buildAndGetNewCurrentBuilder(): ListBuilder {
        notNullParent.add(ElementsList(elements))
        notNullParent.nestedListBuilders.removeLast()
        return if (notNullParent.nestedListBuilders.isNotEmpty())
            notNullParent.nestedListBuilders.last()
        else notNullParent
    }

    fun build() = ElementsList(elements)

    val notNullParent get() = parentBuilder as ListBuilder
}

fun StringBuilder.toSingleValue() =
    takeIf { it.isNotEmpty() && it.contains(Regex("\\d")) }
        ?.toString()
        ?.trim()
        ?.toInt()
        ?.let { SingleValue(it) }

fun parseLine(text: String): ElementsList {
    var builder = ListBuilder(null)
    val cleanedText = text.removeSuffix("\n").removeSurrounding(prefix = "[", suffix = "]")

    val buffer = StringBuilder()

    for (char in cleanedText) {
        when (char) {
            START_LIST_CHAR -> {
                builder = builder.addNewNestedList()
                buffer.clear()
            }
            END_LIST_CHAR -> {
                buffer.toSingleValue()?.let { builder.add(it) }
                builder.buildAndGetNewCurrentBuilder().let { builder = it }
                buffer.clear()
            }
            ',' -> {
                buffer.toSingleValue()?.let { builder.add(it) }
                buffer.clear()
            }
            else -> {
                buffer.append(char)
            }
        }
    }

    buffer.toSingleValue()?.let { builder.add(it) }

    require(builder.parentBuilder == null) { "Parent builder is not null" }

    return builder.build()
}