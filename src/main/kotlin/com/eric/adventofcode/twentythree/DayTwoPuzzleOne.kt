package com.eric.adventofcode.twentythree

fun main() {

}

/**
 * Given a string that starts with "Game xx: ...", return the number represented by xx
 */
fun getGameNumber(line: String): Int {
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
fun getGameResults(line: String): List<Map<String, Int>> {
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
        results.add(parseResult(nextResult))
        startingIndex = nextSemiColonIndex + 2
    }

    return results
}

/**
 * Parse a string of the form "x blue, y green, z red" into a map where the keys are
 * the colors and the values are the numbers represented by x, y, etc.
 */
fun parseResult(results: String): Map<String, Int> {
    var startingIndex = 0
    var allColorsParsed = false
    val parsedResults = mutableMapOf<String, Int>()

    while (!allColorsParsed) {
        val numberEndingIndex = results.indexOf(" ", startingIndex)
        val number = results.substring(startingIndex, numberEndingIndex)

        val nextCommaIndex = results.indexOf(",", startingIndex)
        val color = results.substring(numberEndingIndex + 1, nextCommaIndex)

        parsedResults.put(color, number.toInt())
    }

    return parsedResults
}

fun getColorCount(color: String, results: Map<String, Int>): Int {

}