package de.ddkfm

import java.io.File
import de.ddkfm.DayInterface

class Day1 : DayInterface<File, Int> {
    override fun getInput(): File {
        return File(ClassLoader.getSystemResource("Day1.txt").file)
    }
    override fun check(inputFile: File): Int {
        val input = inputFile
            .readLines()
            .map { it.toInt() }
        val increased = input.mapIndexed { index: Int, number: Int ->
            val prev = input.getOrNull(index - 1)
            return@mapIndexed prev != null && number > prev
        }
            .filter { it }//only true
        return increased.size
    }

    override fun check2(inputFile: File): Int {
        val input = inputFile
            .readLines()
            .map { it.toInt() }
        val groups = mutableMapOf<Int, MutableList<Int>>()
        input.forEach { i ->
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