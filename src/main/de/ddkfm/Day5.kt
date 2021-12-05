package de.ddkfm

import kotlin.math.*

data class Point(
    val x : Int,
    val y : Int
)
data class Line(
    val p1 : Point,
    val p2 : Point
) {
    val directionVektor = Point(p2.x - p1.x, p2.y - p1.y)
    val unitVektor = Point(directionVektor.x.divideOr0(directionVektor.x.absoluteValue), directionVektor.y.divideOr0(directionVektor.y.absoluteValue))
    val length = max(directionVektor.x.divideOr0(unitVektor.x), directionVektor.y.divideOr0(unitVektor.y))
    fun getPoints() : List<Point> {
        return (0..length).map { n -> Point(p1.x + n * unitVektor.x, p1.y + n * unitVektor.y) }
    }

    fun Int.divideOr0(b : Int) : Int {
        if(b == 0)
            return 0
        return this / b
    }
}

class Day5 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        val lines = input.map {
            val points = it.split(" -> ").map { it.split(",").map { it.toInt() } }
            return@map Line(p1 = Point(points[0][0], points[0][1]), p2 = Point(points[1][0], points[1][1]))
        }
        val straightLines = lines.filter { abs(it.unitVektor.x + it.unitVektor.y) == 1 }
        //straightLines.printLines()
        //straightLines.print()
        return straightLines
            .map { it.getPoints() }
            .flatten()
            .groupBy { it }
            .filter { it.value.size > 1 }
            .keys.size
    }

    fun List<Line>.printLines() {
        this.forEach { line ->
            println("[--]${line.p1.x},${line.p1.y} -> ${line.p2.x}, ${line.p2.y}")
            println(line.getPoints().joinToString(separator = ",") { "(${it.x}, ${it.y})" })

        }
    }
    fun List<Line>.print() {
        val flattenPoints = this.map { it.p1 }.union(this.map { it.p2 })
        val xMax = flattenPoints.maxOf { it.x }
        val yMax = flattenPoints.maxOf { it.y }
        val points = this.map { it.getPoints() }.flatten()
        (0 .. yMax).forEach { y ->
            (0 .. xMax).forEach { x ->
                val matchedPoints = points.filter { it.x == x && it.y == y }
                if(matchedPoints.isNotEmpty()) {
                    print("${matchedPoints.size} ")
                } else {
                    print(". ")
                }
            }
            print("\n")
        }
    }

    override fun part2(input: List<String>): Int {
        val lines = input.map {
            val points = it.split(" -> ").map { it.split(",").map { it.toInt() } }
            return@map Line(p1 = Point(points[0][0], points[0][1]), p2 = Point(points[1][0], points[1][1]))
        }
        //lines.print()
        return lines
            .map { it.getPoints() }
            .flatten()
            .groupBy { it }
            .filter { it.value.size > 1 }
            .keys.size
    }

}
