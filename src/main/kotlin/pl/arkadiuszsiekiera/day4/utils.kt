package pl.arkadiuszsiekiera.day4

typealias Id = Int

data class ElvesPair(
    val firstElveIds: List<Id>,
    val secondElveIds: List<Id>
)

fun convertToIdsList(idsRange: String): List<Id> {
    val (start, end) = idsRange.split("-")
    val startId = start.toInt()
    val endId = end.toInt()
    return (startId..endId).toList()
}
