package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInputString("Day05.txt")

    val valueMatrix = markCoordinates(input)
    println("number of points at least two lines overlap: ${getDangerAreaCount(valueMatrix)}")
}

fun getDangerAreaCount(valueMatrix: Array<IntArray>): Int {
    var count = 0
    for (row in valueMatrix) {
        for (point in row) {
            if (point > 1)
                count++
        }
    }
    return count
}

fun markCoordinates(input: List<String>): Array<IntArray> {
    val valueMatrix = Array(1000) { IntArray(1000) { 0 } }

    input.map { item ->
        val points = item.split(" -> ")
        val start = Coordinate(points[0].split(","))
        val end = Coordinate(points[1].split(","))

        when {
            start.x == end.x -> {
                updateVerticalCoordinates(start, end, valueMatrix)
            }
            start.y == end.y -> {
                updateHorizontalCoordinates(start, end, valueMatrix)
            }
            else -> {
                updateDiagonalCoordinates(start, end, valueMatrix)
            }
        }
    }
    return valueMatrix
}

fun updateDiagonalCoordinates(start: Coordinate, end: Coordinate, valves: Array<IntArray>) {
    while (start.x < end.x && start.y < end.y) {
        valves[start.x][start.y]++
        start.x++
        start.y++
    }

    while (start.x > end.x && start.y > end.y) {
        valves[start.x][start.y]++
        start.x--
        start.y--
    }

    while (start.x < end.x && start.y > end.y) {
        valves[start.x][start.y]++
        start.x++
        start.y--
    }

    while (start.x > end.x && start.y < end.y) {
        valves[start.x][start.y]++
        start.x--
        start.y++
    }

    valves[start.x][start.y]++
}

private fun updateHorizontalCoordinates(
    start: Coordinate,
    end: Coordinate,
    valves: Array<IntArray>
) {
    while (start.x < end.x) {
        valves[start.x][start.y]++
        start.x++
    }

    while (start.x > end.x) {
        valves[start.x][start.y]++
        start.x--
    }

    valves[start.x][start.y]++
}

private fun updateVerticalCoordinates(
    start: Coordinate,
    end: Coordinate,
    valves: Array<IntArray>
) {
    while (start.y < end.y) {
        valves[start.x][start.y]++
        start.y++
    }

    while (start.y > end.y) {
        valves[start.x][start.y]++
        start.y--
    }

    valves[start.x][start.y]++
}

class Coordinate(input: List<String>) {
    var x = input[0].toInt()
    var y = input[1].toInt()
}



