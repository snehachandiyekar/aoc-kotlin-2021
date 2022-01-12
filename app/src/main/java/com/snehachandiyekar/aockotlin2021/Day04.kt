package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInputString("Day04.txt")
    val randomNum = input[0].split(",").map { it.toInt() }
    var boardList = readBoards(input)

    part01(randomNum, boardList)
    part02(randomNum, boardList)
}

fun part01(randomNum: List<Int>, boardList: MutableList<Board>) {
    for (num in randomNum) {
        val completedBoard: Board? = markBoardValuesPart01(num, boardList)
        if (completedBoard != null) {
            println("Winning board score ${completedBoard.getScore(num)}")
            break
        }
    }
}

fun part02(randomNum: List<Int>, boardList: MutableList<Board>) {
    for (num in randomNum) {
        val completedBoard: Board? = markBoardValuesPart02(num, boardList)
        if (completedBoard != null) {
            println("Last winning board score ${completedBoard.getScore(num)}")
            break
        }
    }
}

fun markBoardValuesPart01(num: Int, boardList: MutableList<Board>): Board? {
    for (index in boardList.indices) {
        val board = boardList[index]
        board.markValue(num)

        if (board.isBoardComplete()) {
            return board
        }
    }
    return null
}

fun markBoardValuesPart02(num: Int, boardList: MutableList<Board>): Board? {
    val iterator = boardList.iterator()
    while (iterator.hasNext()){
        val board = iterator.next()
        board.markValue(num)
        if (board.isBoardComplete()) {
            if (boardList.size == 1){
                return board
            }
            iterator.remove()
        }
    }

    return null
}

//THIS LOGIC NEEDS IMPROVEMENT
private fun readBoards(input: List<String>): MutableList<Board> {
    var boardList = mutableListOf<Board>()
    var board = Board()
    for (inputRow in 2 until input.size) {
        val split = input[inputRow].split("\\s+".toRegex())
        var boardRow = mutableListOf<BoardEntity>()

        for (item in split) {
            if (item.trim().isNotEmpty()) {
                val entity = BoardEntity(item.toInt())
                boardRow.add(entity)
            }
        }

        if (boardRow.isEmpty()) {
            boardList.add(board)
            board = Board()
        } else {
            board.addRow(boardRow)
        }
    }
    boardList.add(board)
    return boardList
}

data class BoardEntity(val value: Int, var isMarked: Boolean = false)

class Board {
    var board = mutableListOf<List<BoardEntity>>()
    fun addRow(row: List<BoardEntity>) {
        board.add(row)
    }

    fun markValue(num: Int) {
        for (i in board.indices) {
            for (entity in board[i]) {
                if (entity.value == num) {
                    entity.isMarked = true
                    return
                }
            }
        }
    }

    fun isBoardComplete(): Boolean {
        return isRowComplete() || isColumnComplete()
    }

    private fun isColumnComplete(): Boolean {
        var markedCount: Int
        for (index in board.indices) {
            markedCount = 0
            for (row in board) {
                if (row[index].isMarked) {
                    markedCount++
                    if (markedCount == 5) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun isRowComplete(): Boolean {
        var markedCount: Int
        for (row in board) {
            markedCount = 0
            for (entity in row) {
                if (entity.isMarked) {
                    markedCount++
                    if (markedCount == 5) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun getScore(num: Int): Int {
        var sum = 0
        for (row in board) {
            for (entity in row) {
                if (!entity.isMarked) {
                    sum += entity.value
                }
            }
        }
        return sum * num
    }
}