// Advent of Code
// Day 07
// https://adventofcode.com/2020/day/07


import java.io.File

data class Bag(val name: String, val containsGold: Boolean, val childBags: ArrayList<Bag>)

fun part1(filename: String) {

    var allBags = hashMapOf<String, List<String> >()

    File(filename).forEachLine {
        val current = it.substringBefore("contain").trim().substringBefore("bags")
        val childrenStr = it.substringAfter("contain").trim().trim('.')

        var children = listOf<String>()
        if (!childrenStr.equals("no other bags")) {
            // parse child bag colors
            val cArr = childrenStr.split(',')
            children = cArr.map { it.substring(2).substringBefore("bag").trim() }
        }
        // add current bag and children into map
        allBags[current] = children
    }

    // count shiny golds
    fun canContainGold(childBags: List<String>): Boolean {
        if (childBags.isEmpty()) {
            println("childbags are $childBags")
            return false
        }

        if (childBags.contains("shiny gold")) {
            println("child bags are $childBags")
            return true
        } else {
            val result = childBags.map {
                println("looking into $it")
                canContainGold(allBags.getOrDefault(it.trim(), listOf<String>() ))
            }
            // return true if any of the recursive calls are true
            println("child bags are $childBags, result is $result")
            return result.any { it }
        }
    }

    println("All bags: $allBags")
    var count = 0
    for ((_, bags) in allBags) {
        if (canContainGold(bags)) {
            count++
            println("increased count to $count")
        }
    }

    println(count)
}


fun part2(filename: String) {

    File(filename).forEachLine {
        // access each line with `it` (type String)
    }

}

fun main(args: Array<String>) {
    part1("input_sample")
//    part2("input")
}