package com.eric.adventofcode.twentythree

import java.io.File

/**
 * https://adventofcode.com/2023/day/1#part2
 */
fun main() {
    DayTwoPuzzleTwo().play()
}

class DayOnePuzzleTwo {
    private val inputDataFilePath = "/Users/ericoconnor/src/AdventOfCode/src/main/resources/adventofcode/Day1Puzzle1Input.txt"
    private val numberMap = mapOf(
        "zero" to "0",
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun play() {
        var calibrationValueSum = 0

        val inputDataFile = File(inputDataFilePath)
        inputDataFile.forEachLine { line ->
            val firstNumber = getFirstNumber(line)
            val lastNumber = getLastNumber(line)
            val calibrationValue = "$firstNumber$lastNumber".toInt()
            calibrationValueSum += calibrationValue
            println("Line = $line, calibration value = $calibrationValue, new total = $calibrationValueSum")
        }
    }

    private fun getFirstNumber(line: String): String {
        var firstDigitIndex = -1
        var firstNumberWordIndex = -1
        var firstDigit = ""
        var firstNumberWord = ""

        numberMap.keys.forEach { numberWord ->
            val index = line.indexOf(numberWord)
            if (index >= 0 && (index < firstNumberWordIndex || firstNumberWordIndex == -1)) {
                firstNumberWordIndex = index
                firstNumberWord = numberWord
            }
        }

        numberMap.values.forEach {numberString ->
            val index = line.indexOf(numberString)
            if (index >= 0 && (index < firstDigitIndex || firstDigitIndex == -1)) {
                firstDigitIndex = index
                firstDigit = numberString
            }
        }

        return when {
            firstNumberWordIndex == -1 || firstDigitIndex < firstNumberWordIndex -> firstDigit
            else -> numberMap[firstNumberWord]!!
        }
    }


    private fun getLastNumber(line: String): String {
        var lastDigitIndex = -1
        var lastNumberWordIndex = -1
        var lastDigit = ""
        var lastNumberWord = ""

        numberMap.keys.forEach { numberWord ->
            val index = line.lastIndexOf(numberWord)
            if (index >= 0 && index > lastNumberWordIndex) {
                lastNumberWordIndex = index
                lastNumberWord = numberWord
            }
        }

        numberMap.values.forEach {numberString ->
            val index = line.lastIndexOf(numberString)
            if (index >= 0 && index > lastDigitIndex) {
                lastDigitIndex = index
                lastDigit = numberString
            }
        }

        return when {
            lastNumberWordIndex == -1 || lastDigitIndex > lastNumberWordIndex -> lastDigit
            else -> numberMap[lastNumberWord]!!
        }
    }
}

