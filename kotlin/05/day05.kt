// Advent of Code
// Day 05
// https://adventofcode.com/2020/day/5

import java.io.File

data class Seat(val row: Int, val col: Int)

fun part1(filename: String) {
    var max = 0

    File(filename).forEachLine {
        val rowStr = it.substring(0, 7)
        val colStr = it.substring(7, it.length)

        val row = parser(rowStr, 0, 127, 'F', 'B')
        val col = parser(colStr, 0, 7, 'L', 'R')
        val id = row * 8 + col
//        println("Row: $row, Col: $col, SeatID: $id")
        if (id > max) {
            max = id
        }
    }

    println("Part 1 max is $max")
}

fun parser(pass: String, min: Int, max: Int, lowerBound: Char, upperBound: Char): Int {
    var lower = min
    var upper = max
    for (letter in pass) {
        if (letter == lowerBound) {
            val split = (upper - lower) / 2
            upper = lower + split
        } else if (letter == upperBound) {
            val split = (upper - lower) / 2 + 1
            lower = lower + split
        }
    }
    return lower
}

fun part2(filename: String) {
    var seats = hashMapOf<Int, Seat>()

    File(filename).forEachLine {
        val rowStr = it.substring(0, 7)
        val colStr = it.substring(7, it.length)

        val row = parser(rowStr, 0, 127, 'F', 'B')
        val col = parser(colStr, 0, 7, 'L', 'R')
        val id = row * 8 + col
        val seat = Seat(row, col)
        seats[id] = seat
    }

    // find empty seat
    var missing = 0
    var sortedSeats = seats.toSortedMap()
    for (i in sortedSeats.firstKey()..sortedSeats.lastKey()) {
        if (sortedSeats.containsKey(i)) {
            continue
        } else {
            missing = i
        }
    }
    println("Part 2 seat ID is $missing")

}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}