package com.eric.adventofcode.twentythree

import java.io.File

fun main() {
    DayTwoPuzzleTwo().play()
}

class DayTwoPuzzleTwo {
    private val inputDataFilePath = "/Users/ericoconnor/src/AdventOfCode/src/main/resources/adventofcode/Day2Puzzle1Input.txt"

    fun play() {
        var powerSum = 0

        val inputDataFile = File(inputDataFilePath)
        inputDataFile.forEachLine { line ->
            val gameResults = getGameResults(line)

            // Get the largest number of cubes for each color across all game results
            var maxNumberRed = 0
            var maxNumberGreen = 0
            var maxNumberBlue = 0
            gameResults.forEach {  result ->
                val redCount = getColorCount("red", result)
                if (redCount > maxNumberRed) maxNumberRed = redCount
                val greenCount = getColorCount("green", result)
                if (greenCount > maxNumberGreen) maxNumberGreen = greenCount
                val blueCount = getColorCount("blue", result)
                if (blueCount > maxNumberBlue) maxNumberBlue = blueCount
            }

            val power = calculatePower(maxNumberRed, maxNumberGreen, maxNumberBlue)
            println("Power for game ${getGameNumber(line)} = $power")

            powerSum += power
        }

        println("Sum of powers is $powerSum")
    }

    /**
     * Given a string of this format:
     *
     * Game xx: x blue, y green, z red; x red, y blue; x blue, y red
     *
     * Return an array of maps representing the colors and their counts from each turn
     */
    private fun getGameResults(line: String): List<Map<String, Int>> {
        var allResultsParsed = false
        var startingIndex = line.indexOf(":") + 2
        val results = mutableListOf(mapOf<String, Int>())

        while (!allResultsParsed) {
            val nextSemiColonIndex = line.indexOf(";", startingIndex)
            val nextResult = if (nextSemiColonIndex == -1) {
                // Last result
                allResultsParsed = true
                line.substring(startingIndex)
            } else {
                line.substring(startingIndex, nextSemiColonIndex)
            }

            results.add(parseColorCounts(nextResult))
            startingIndex = nextSemiColonIndex + 2
        }

        return results
    }

    /**
     * Parse a string of the form "x blue, y green, z red" into a map where the keys are
     * the colors and the values are the numbers represented by x, y, etc.
     */
    private fun parseColorCounts(colorCounts: String): Map<String, Int> {
        var startingIndex = 0
        var allColorsParsed = false
        val colorCountMap = mutableMapOf<String, Int>()

        while (!allColorsParsed) {
            val numberEndingIndex = colorCounts.indexOf(" ", startingIndex)
            val number = colorCounts.substring(startingIndex, numberEndingIndex)

            val nextCommaIndex = colorCounts.indexOf(",", startingIndex)
            val color = if (nextCommaIndex == -1) {
                // We've reached the last result
                allColorsParsed = true
                colorCounts.substring(numberEndingIndex + 1)
            } else {
                colorCounts.substring(numberEndingIndex + 1, nextCommaIndex)
            }

            colorCountMap[color] = number.toInt()
            startingIndex = nextCommaIndex + 2
        }

        return colorCountMap
    }

    /**
     * Get the count of a given color from the provided map. Return 0 if the color
     * is not represented in the map.
     */
    private fun getColorCount(color: String, colorCountMap: Map<String, Int>): Int {
        return colorCountMap[color] ?: 0
    }

    private fun calculatePower(numRedCubes: Int, numGreenCubes: Int, numBlueCubes: Int): Int {
        val nonZeroNumbers = mutableListOf<Int>()

        // This may not be necessary, but if any of the counts is 0, the whole result
        // will be 0, which I don't think is what we want?
        if (numRedCubes > 0) nonZeroNumbers.add(numRedCubes)
        if (numGreenCubes > 0) nonZeroNumbers.add(numGreenCubes)
        if (numBlueCubes > 0) nonZeroNumbers.add(numBlueCubes)

        return nonZeroNumbers.reduce { acc, count -> acc * count }
    }

    /**
     * Given a string that starts with "Game xx: ...", return the number represented by xx
     */
    private fun getGameNumber(line: String): Int {
        val numberStr = line.substring(5, line.indexOf(":"))
        return numberStr.toInt()
    }

}