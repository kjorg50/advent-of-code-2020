// Advent of Code
// Day 04
// https://adventofcode.com/2020/day/4

import java.io.File

fun part1(filename: String) {

    val entireFile = File(filename).readText(Charsets.UTF_8)
    val passports = entireFile.split("\n\n").map { it.replace("\n", " ") }

    val passportMap = arrayListOf< Map<String, String> >()
    for (passport in passports) {
        val p = passport.split(" ").map { it.substringBefore(":") to it.substringAfter(":") }.toMap()
//        println(p)
        passportMap.add(p)
    }

    var count = 0
    for (pass in passportMap) {
        if (fieldsPresent(pass)) count++
    }

    println("Part 1 = $count")
}

fun fieldsPresent(pMap: Map<String, String>): Boolean {
    if (pMap.keys.size == 8) return true
    if (pMap.keys.size == 7 && !pMap.keys.contains("cid")) return true
    else return false
}

fun validateFields(pMap: Map<String, String>): Boolean {
    // Birth year
    val byr = pMap.getOrDefault("byr", "0").toInt()
    if (byr < 1920 || byr > 2002) return false

    // Issue year
    val iyr = pMap.getOrDefault("iyr", "0").toInt()
    if (iyr < 2010 || iyr > 2020) return false

    // Expiration year
    val eyr = pMap.getOrDefault("eyr", "0").toInt()
    if (eyr < 2020 || eyr > 2030) return false

    // Height
    val hgt = pMap.getOrDefault("hgt", "0")
    if (!(hgt.contains("in") || hgt.contains("cm"))) return false

    if (hgt.contains("in")) {
        val inches = hgt.substringBefore("in")
        val inchNum = inches.toInt()
        if (inchNum < 59 || inchNum > 76) return false
    } else if (hgt.contains("cm")) {
        val centimeters = hgt.substringBefore("cm")
        val centNum = centimeters.toInt()
        if (centNum < 150 || centNum > 193) return false
    }

    // hair color
    val hcl = pMap.getOrDefault("hcl", "0")
    if (!(hcl.contains("#"))) return false
    val hclVal = hcl.substringAfter("#")
    if (!(hclVal.length == 6 && isHex(hclVal))) return false

    // eye color
    val eyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    if (!eyeColors.contains(pMap["ecl"])) return false

    // passport ID
    val pid = pMap.getOrDefault("pid", "0")
    if (pid.length != 9 || !isDigits(pid)) return false

    // If nothing failed, then it's valid
    return true
}

fun isHex(chars: String): Boolean {
    return chars.filter { it in 'a'..'f' || it in '0'..'9' }
            .length == chars.length
}

fun isDigits(chars: String): Boolean {
    return chars.filter { it in '0'..'9' }
            .length == chars.length
}

fun part2(filename: String) {
    val passportMap = arrayListOf< Map<String, String> >()
    var count = 0

    val entireFile = File(filename).readText(Charsets.UTF_8)
    val passports = entireFile.split("\n\n").map { it.replace("\n", " ") }

    for (passport in passports) {
        val p = passport.split(" ").map { it.substringBefore(":") to it.substringAfter(":") }.toMap()
        passportMap.add(p)
    }

    for (pass in passportMap) {
        if (fieldsPresent(pass) && validateFields(pass)) count++
    }

    println("Part 2 is $count")
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}