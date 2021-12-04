package de.ddkfm

data class Board(
    val board : List<List<Int>> = emptyList(),
    val marked : List<MutableList<Boolean>> = board.map { it.map { false }.toMutableList() }
) {
    fun find(number : Int) : Int {
        var find = -1
        board.indices.map { i ->
            board[i].indices.map { j ->
                if(board[i][j] == number && !marked[i][j]) {
                    marked[i][j] = true
                    find = number
                }
            }
        }
        return find
    }

    fun hasWon() : Boolean {
        val row = marked.any { it.all { it } }
        val columns = (0 until 5)
            .map { i -> marked.map { it[i] } }
            .any { it.all { it } }
        return row || columns
    }
    fun unmarkedSum() : Int {
        val markedList = marked.flatten().toList()
        return board.flatten()
            .filterIndexed { index, i -> !markedList[index] }
            .sum()
    }

    fun print() {
        board.forEachIndexed { i, list ->
            list.forEachIndexed { j, number ->
                val num = if(number < 10) " $number" else "$number"
                val mark = if(marked[i][j])
                    "[$num]"
                else
                    " $num "
                print("$mark ")
            }
            print("\n")
        }
    }
}
class Day4 : DayInterface<List<String>, Int> {
    override fun part1(input: List<String>): Int {
        val inputs = input.first().split(",").map { it.toInt() }
        val boards = input.getBoards()
        inputs.forEach { input ->
            boards.forEach { board ->
                val find = board.find(input)
                if(find != -1) {
                    //println("find $find on board $board")
                    if(board.hasWon()) {
                        println("NICE: $find")
                        val unmarked = board.unmarkedSum()
                        return unmarked * find
                    }
                }
            }
        }
        return -1
    }

    fun List<String>.getBoards(): List<Board> {
        return this.drop(1)
            .filterNot { it.isEmpty() }
            .chunked(5)
            .map { chunk ->
                val board = chunk
                        .map {
                            it.split(" ")
                                .filter { it.isNotEmpty() }
                                .map { it.toInt() }
                        }
                return@map Board(board)
            }
    }

    override fun part2(input: List<String>): Int {
        val inputs = input.first().split(",").map { it.toInt() }
        val boards = input.getBoards()
        val winningBoards = mutableListOf<Int>()
        inputs.forEach { input ->
            boards.forEachIndexed { index, board ->
                val find = board.find(input)
                if(find != -1) {
                    //println("find $find on board $board")
                    if(board.hasWon()) {
                        println("Board $index has won:")
                        board.print()
                        if(!winningBoards.contains(index))
                            winningBoards.add(index)
                        if(winningBoards.size == boards.size) {
                            val unmarked = board.unmarkedSum()
                            return unmarked * find
                        }
                    }
                }
            }
        }
        return -1
    }
}
