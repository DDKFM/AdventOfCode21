package de.ddkfm

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class AdventOfCodeTests {

    private fun getInputFile(dir : String) : File {
        return File(ClassLoader.getSystemResource("$dir/Input.txt").file)
    }

    private fun getExampleFile(dir : String) : File {
        return File(ClassLoader.getSystemResource("$dir/Example.txt").file)
    }

    private fun <D : DayInterface<List<String>, Int>> withDay(day : D, dayFunc : D.() -> Unit) {
        day.dayFunc()
    }

    @Test
    fun day1() {
        withDay(Day1()) {
            val example = getExampleFile("day1").readLines()
            val input = getInputFile("day1").readLines()

            assertEquals(7, part1(example))
            assertEquals(1292, part1(input))

            assertEquals(5, part2(example))
            assertEquals(1262, part2(input))
        }
    }

    @Test
    fun day2() {

        withDay(Day2()) {
            val example = getExampleFile("day2").readLines()
            val input = getInputFile("day2").readLines()

            assertEquals(150, part1(example))
            assertEquals(2120749, part1(input))

            assertEquals(900, part2(example))
            assertEquals(2138382217, part2(input))
        }
    }
}