package uk.org.fca.adventofcode.y2023.day1

data class LocationList(val location1: Int, val location2: Int) {
    companion object {
        fun parse(rawLocationsData: String): LocationList {
            val result = rawLocationsData.split(" ").filter { it.isNotEmpty() }
            return LocationList(result[0].toInt(), result[1].toInt())
        }

        fun parse(rawLocationsData: List<String>): List<LocationList> = rawLocationsData.map { parse(it) }
    }
}