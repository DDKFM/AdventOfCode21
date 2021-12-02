package de.ddkfm

class Day1 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        val integers = input.map { it.toInt() }
        val increased = integers.mapIndexed { index: Int, number: Int ->
            val prev = integers.getOrNull(index - 1)
            return@mapIndexed prev != null && number > prev
        }
            .filter { it }//only true
        return increased.size
    }

    override fun part2(input: List<String>): Int {
        val integers = input.map { it.toInt() }
        val groups = mutableMapOf<Int, MutableList<Int>>()
        integers.forEach { i ->
            groups[groups.size - 2]?.add(i)
            groups[groups.size - 1]?.add(i)
            groups[groups.size] = mutableListOf(i)
        }
        val groupSums = groups.map { it.value.sum() }

        val increased = groupSums.mapIndexed { index: Int, number: Int ->
            val prev = groupSums.getOrNull(index - 1)
            return@mapIndexed prev != null && number > prev
        }
            .filter { it }//only true
        return increased.size
    }
}