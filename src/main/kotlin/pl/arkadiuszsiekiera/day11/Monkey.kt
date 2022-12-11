package pl.arkadiuszsiekiera.day11

import java.util.Queue

typealias Item = Long
typealias WorryManger = (value: Item) -> Item
typealias Operation = (old: Item) -> Item

data class Throw(
    val to: Int,
    val item: Item,
)
data class Monkey(
    private val items: Queue<Item>,
    private val operation: Operation,
    val testDivider: Int,
    val trueResultMonkeyIndex: Int,
    val falseResultMonkeyIndex: Int,
) {

    var itemsInspected = 0
        private set

    fun inspect(worryManager: WorryManger) = items.poll()?.let { item ->
        itemsInspected++

        val newItem = worryManager(operation(item))

        Throw(
            to = if (newItem % testDivider == 0L)
                trueResultMonkeyIndex else falseResultMonkeyIndex,
            item = newItem
        )
    }

    fun catchItem(item: Item) {
        items.add(item)
    }

}
