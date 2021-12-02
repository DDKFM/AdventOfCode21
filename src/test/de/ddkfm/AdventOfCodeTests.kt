package de.ddkfm

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AdventOfCodeTests {

    @Test
    fun day1() {
        val test = Day1()
        val result = test.check(test.getInput())
        println(result)
        //assertEquals(7, result)
        assertEquals(1292, result)

        val result2 = test.check2(test.getInput())
        println(result2)
        assertEquals(1262, result2)
    }

    @Test
    fun day2() {
        val test = Day2()
        val result = test.check(test.getInput())
        println(result)
        //assertEquals(150, result)
        assertEquals(2120749, result)

        val result2 = test.check2(test.getInput())
        println(result2)
        //assertEquals(900, result2)
        assertEquals(2138382217, result2)
    }
}