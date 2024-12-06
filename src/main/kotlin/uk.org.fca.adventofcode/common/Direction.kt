package uk.org.fca.adventofcode.common


enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    val opposite get(): Direction =
        when (this) {
            NORTH -> SOUTH
            EAST -> WEST
            SOUTH -> NORTH
            WEST -> EAST
        }

    val rotateRight get(): Direction =
        when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }

    val rotateLeft get(): Direction =
        when (this) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
        }

    val movementStep get(): Coord =
        when (this) {
            NORTH -> Coord(0, -1)
            EAST -> Coord(1, 0)
            SOUTH -> Coord(0, 1)
            WEST -> Coord(-1, 0)
        }
}
