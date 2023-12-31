package com.eric.adventofcode.twentythree

import java.io.File

fun main() {
    DayTwoPuzzleOne().play()
}

class DayTwoPuzzleOne {
    private val inputDataFilePath = "/Users/ericoconnor/src/AdventOfCode/src/main/resources/adventofcode/Day2Puzzle1Input.txt"

    fun play() {
        val numRedCubes = 12
        val numGreenCubes = 13
        val numBlueCubes = 14
        var gameIdSum = 0

        val inputDataFile = File(inputDataFilePath)
        inputDataFile.forEachLine { game ->
            val gameNumber = getGameNumber(game)
            val gameResults = getGameResults(game)
            gameResults.forEach { result ->
                if (getColorCount("red", result) > numRedCubes ||
                    getColorCount("green", result) > numGreenCubes ||
                    getColorCount("blue", result) > numBlueCubes
                ) {
                    // This game is not possible
                    println("Game $gameNumber IS NOT possible")
                    return@forEachLine
                }
            }

            println("Game $gameNumber IS possible")
            gameIdSum += gameNumber
        }

        println("Result = $gameIdSum")
    }

    /**
     * Given a string that starts with "Game xx: ...", return the number represented by xx
     */
    private fun getGameNumber(line: String): Int {
        val numberStr = line.substring(5, line.indexOf(":"))
        return numberStr.toInt()
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
    }}
