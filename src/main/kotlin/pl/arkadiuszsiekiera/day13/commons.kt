package pl.arkadiuszsiekiera.day13

import pl.arkadiuszsiekiera.day13.Order.UNDEFINED
import pl.arkadiuszsiekiera.day13.Order.RIGHT
import pl.arkadiuszsiekiera.day13.Order.WRONG

data class ElementsList(val elements: List<Element>) : Element {

    fun compareLists(other: ElementsList): Order {
        val leftList = elements
        val rightList = other.elements

        val result = leftList.zip(rightList).asSequence()
            .map { (leftValue, rightValue) -> leftValue.orderOfElements(rightValue) }
            .filter { it != UNDEFINED }
            .firstOrNull()

        return when {
            result != null -> result
            leftList.size == rightList.size -> UNDEFINED
            leftList.size < rightList.size -> RIGHT
            else -> WRONG
        }
    }

    override fun toString(): String {
        return elements.joinToString(prefix = "[", postfix = "]", separator = ", ") {
            it.toString()
        }
    }

    companion object {
        fun singleElement(element: Element) = ElementsList(listOf(element))
    }
}

data class SingleValue(val value: Int) : Element {

    fun compareValues(other: SingleValue): Order {
        val result = value.compareTo(other.value)
        return when {
            result == 0 -> UNDEFINED
            result < 0 -> RIGHT
            else -> WRONG
        }
    }

    fun toElementsList() = ElementsList(listOf(this))

    override fun toString() = value.toString()
}