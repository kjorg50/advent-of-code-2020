// Advent of Code
// Day 03
// https://adventofcode.com/2020/day/3

import java.io.File

fun part1(filename: String) {
    var trees = arrayListOf<String>()

    File(filename).forEachLine {
        trees.add(it)
    }

    println(traverse(trees, 3, 1))
}

fun traverse(treeArray: ArrayList<String>, right: Int, down: Int): Long {
    var count = 0L
    var col = 0
    var firstLine = true

    for (row in treeArray.indices step down) {
        if (firstLine) {
            firstLine = false
            continue
        }
        col = (col + right) % treeArray[row].length
//        println("$row, $col")
        if (treeArray[row].get(col) == '#') count++
    }

    return count
}

fun part2(filename: String) {
    var trees = arrayListOf<String>()

    File(filename).forEachLine {
        trees.add(it)
    }

    val a = traverse(trees, 1, 1)
    val b = traverse(trees, 3, 1)
    val c = traverse(trees, 5, 1)
    val d = traverse(trees, 7, 1)
    val e = traverse(trees, 1, 2)
    var product: Long = a * b * c * d * e

    println(product)
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}