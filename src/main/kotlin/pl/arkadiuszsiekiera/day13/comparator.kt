package pl.arkadiuszsiekiera.day13

enum class Order {
    RIGHT,
    WRONG,
    UNDEFINED
}

fun Element.orderOfElements(right: Element): Order {
    val left = this
    return when {
        left is SingleValue && right is SingleValue -> left.compareValues(right)
        left is ElementsList && right is ElementsList -> left.compareLists(right)
        else -> {
            val (leftList, rightList) = if (left is SingleValue) {
                left.toElementsList() to right as ElementsList
            } else {
                left as ElementsList to (right as SingleValue).toElementsList()
            }
            leftList.compareLists(rightList)
        }
    }
}