package com.eric.adventofcode.twentythree

import java.io.File

fun main() {
    DayOnePuzzleOne().play()
}

class DayOnePuzzleOne {
    private val inputDataFilePath = "/Users/ericoconnor/src/AdventOfCode/src/main/resources/adventofcode/Day1Puzzle1Input.txt"

    fun play() {
        var calibrationValueSum = 0

        val inputDataFile = File(inputDataFilePath)
        inputDataFile.forEachLine { line ->
            val characters = line.toList()
            val firstDigit = characters.find { it.isDigit() }
            if (firstDigit != null) {
                val lastDigit = characters.findLast { it.isDigit() }
                val calibrationValue = "$firstDigit$lastDigit".toInt()
                calibrationValueSum += calibrationValue
                println("Line = $line, calibration value = $calibrationValue, new total = $calibrationValueSum")
            } else {
                println("Found input line without digit: $line")
            }
        }
    }
}