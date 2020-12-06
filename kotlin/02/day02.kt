// Advent of Code
// Day 01
// https://adventofcode.com/2020/day/1

import java.io.File

data class PasswordRule(val min: Int, val max: Int, val letter: Char, val password: String)
data class PasswordRule2(val idx1: Int, val idx2: Int, val letter: Char, val password: String)

fun isValid(pr: PasswordRule): Boolean {
    val count = pr.password.filter { it == pr.letter}.count()
    return (count >= pr.min && count <= pr.max)
}

fun isValid(pr: PasswordRule2): Boolean{
    val first = pr.password[pr.idx1-1] == pr.letter
    val second = pr.password[pr.idx2-1] == pr.letter
    return first xor second
}

fun part1(filename: String) {
//    val passwords = arrayListOf<PasswordRule>()
    var count = 0

    File(filename).forEachLine {
        val parts = it.split(" ")
        val minMax = parts[0].split("-")
        val l = parts[1].take(1).single()
        val p = parts[2]
        val pr = PasswordRule(minMax[0].toInt(), minMax[1].toInt(), l.toChar(), p)
        if (isValid(pr)) count++
    }

    println("Part 1 count is $count")
}

fun part2(filename: String) {
    var count = 0

    File(filename).forEachLine {
        val parts = it.split(" ")
        val minMax = parts[0].split("-")
        val l = parts[1].take(1).single()
        val p = parts[2]
        val pr = PasswordRule2(minMax[0].toInt(), minMax[1].toInt(), l.toChar(), p)
        if (isValid(pr)) count++
    }

    println("Part 2 count is $count")
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}