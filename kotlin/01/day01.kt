// Advent of Code
// Day 01
// https://adventofcode.com/2020/day/1

import java.io.File

fun part1(filename: String) {
    val numbers = arrayListOf<Int>()

    File(filename).forEachLine {
        numbers.add(it.toInt())
    }

    for (i in numbers.indices) {
        for (j in i+1..numbers.size-1) {
//            println(numbers)
//            println("first is ${numbers[i]}, second is ${numbers[j]}, first + second = ${numbers[i]+numbers[j]}")
            if ((numbers[i] + numbers[j]) == 2020) {
                println("Part 1 = ${numbers[i] * numbers[j]}")
                return
            }
        }
    }
}

fun part2(filename: String) {
    val numbers = arrayListOf<Int>()

    File(filename).forEachLine {
        numbers.add(it.toInt())
    }

    for (i in numbers.indices) {
        for (j in i+1..numbers.size-1) {
            for (k in j+1..numbers.size-1) {
//                println(numbers)
//              println("first is ${numbers[i]}, second is ${numbers[j]}, first + second = ${numbers[i]+numbers[j]}")
                if ((numbers[i] + numbers[j] + numbers[k]) == 2020) {
                    println("Part 2 = ${numbers[i] * numbers[j] * numbers[k]}")
                    return
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    part1("input")
    part2("input")
}