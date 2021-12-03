package de.ddkfm

class Day3 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        val inverted = mutableListOf<String>()
        input.forEach { row ->
            for(i in row.indices) {
                if(inverted.size > i)
                    inverted[i] += row[i].toString()
                else
                    inverted.add(row[i].toString())
            }
        }
        println(inverted)
        val sums = inverted.map { it.groupBy { it }.mapValues { it.value.size } }
        println(sums)
        val gamma = sums.map { it.maxByOrNull { it.value } }.map{ it?.key }.joinToString(separator = "")
        val epsilon = sums.map { it.minByOrNull { it.value }}.map{ it?.key }.joinToString(separator = "")
        println("$gamma, $epsilon")
        return gamma.toInt(2) * epsilon.toInt(2)
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
            val mostCommonBit = getLeastCommonBits(recognizableChars)
            remaining = remaining.filter { it[i] == mostCommonBit }.toMutableList()
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