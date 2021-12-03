package de.ddkfm

class Day3 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        val maxLength = input.maxOf { it.length }
        val rates = (0 until maxLength).map { i ->
            var recognizableChars = input.map { it[i] }
            val mostCommonBit = getMostCommonBits(recognizableChars)
            val leastCommonBit = getLeastCommonBits(recognizableChars)
            return@map mostCommonBit to leastCommonBit
        }
        val gamma = rates.joinToString(separator = "") { it.first.toString() }.toInt(2)
        val epsilon = rates.joinToString(separator = "") { it.second.toString() }.toInt(2)
        return gamma * epsilon
    }

    override fun part2(input: List<String>): Int {
        var remaining = input.toMutableList()
        var i = 0;
        while(remaining.size > 1) {
            var recognizableChars = remaining.map { it[i] }
            val mostCommonBit = getMostCommonBits(recognizableChars)
            remaining = remaining.filter { it[i] == mostCommonBit }.toMutableList()
            i++
        }
        val oxygenRating = remaining.first().toInt(2)

        remaining = input.toMutableList()
        i = 0;
        while(remaining.size > 1) {
            var recognizableChars = remaining.map { it[i] }
            val lestCommonBit = getLeastCommonBits(recognizableChars)
            remaining = remaining.filter { it[i] == lestCommonBit }.toMutableList()
            i++
        }
        val co2Rating = remaining.first().toInt(2)

        return co2Rating * oxygenRating
    }
    private fun getMostCommonBits(chars : List<Char>) : Char {
        return when {
            chars.filter { it == '1' }.size >= chars.filter { it == '0' }.size -> '1'
            else -> '0'
        }
    }

    private fun getLeastCommonBits(chars : List<Char>) : Char {
        return when {
            chars.filter { it == '1' }.size >= chars.filter { it == '0' }.size -> '0'
            else -> '1'
        }
    }
}