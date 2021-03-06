package de.ddkfm

import java.io.File

enum class CommandType {
    FORWARD,
    DOWN,
    UP;
    companion object {
        fun parse(str : String) : CommandType {
            return CommandType.valueOf(str.uppercase())
        }
    }
}
data class Command(
    val type : CommandType,
    val unit : Int
) {
    fun sum() : Int {
        return when(type) {
            CommandType.FORWARD, CommandType.DOWN -> unit
            CommandType.UP -> -unit
        }
    }
}
class Day2 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        return input
            .map { Command(CommandType.parse(it.split(" ")[0]), it.split(" ")[1].toInt()) }
            .groupBy { when(it.type) {
                CommandType.FORWARD -> "horizontal"
                CommandType.UP, CommandType.DOWN -> "depth"
            } }
            .map { it.key to it.value.sumOf { command -> command.sum() } }
            .toMap()
            .values
            .reduce{acc, i -> acc * i }
    }

    override fun part2(input: List<String>): Int {
        var aim = 0;
        var depth = 0;
        var horizontal = 0;
        val commands = input
            .map { Command(CommandType.parse(it.split(" ")[0]), it.split(" ")[1].toInt()) }
        commands.forEach { command ->
            when(command.type) {
                CommandType.DOWN -> aim += command.unit
                CommandType.UP -> aim -= command.unit
                CommandType.FORWARD -> {
                    horizontal += command.unit
                    depth += aim * command.unit
                }
            }
        }
        return horizontal * depth
    }
}