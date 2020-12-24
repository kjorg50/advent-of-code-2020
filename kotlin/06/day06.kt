// Advent of Code
// Day 06
// https://adventofcode.com/2020/day/6

import java.io.File

fun part1(filename: String) {

    val entireFile = File(filename).readText(Charsets.UTF_8)
    val answers = entireFile.split("\n\n").map { it.replace("\n", " ") }

    var boardingGroups = arrayListOf< Set<Char> >()
    for (line in answers) {
        var aSet = hashSetOf<Char>()
        for (ch in line) {
            if (ch != ' ') {
                aSet.add(ch)
            }
        }
        boardingGroups.add(aSet)
    }

    var sum = 0
    for (bg in boardingGroups) {
        sum += bg.size
    }

    println("Part 1 sum is $sum")
}

fun part2(filename: String) {

    val entireFile = File(filename).readText(Charsets.UTF_8)
    val answers = entireFile.split("\n\n").map { it.replace("\n", " ") }

    var totalSum = 0

    for (group in answers) {
        val persons = group.split(" ")
        var answerCount = hashMapOf<Char, Int>()
        for (p in persons) {
            for (ch in p) {
                if (answerCount.contains(ch)) {
                    answerCount[ch] = answerCount.getOrDefault(ch, 0) + 1
                } else {
                    answerCount[ch] = 1
                }
            }
        }

        // sum the ones where all the people put the same answer
        // i.e. where the value in answerCount is equal to the number of people
        for ((_, value) in answerCount) {
            if (value == persons.size) {
                totalSum++
            }
        }
    }

    println("Part 2 sum is $totalSum")
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}