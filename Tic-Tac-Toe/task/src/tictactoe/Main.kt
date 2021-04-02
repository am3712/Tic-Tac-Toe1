package tictactoe

object XoGame {
    private var turn = 'X'

    private val grid = arrayOf(
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_')
    )


    private fun readAndValidateCoordinates() {
        var x: Int
        var y: Int
        var inputValues: List<String>
        while (true) {
            println("Enter the coordinates: ")
            inputValues = readLine()!!.split(" ")
            try {
                x = inputValues[0].toInt() - 1
                y = inputValues[1].toInt() - 1
                if (x !in 0..2 || y !in 0..2) {
                    println("Coordinates should be from 1 to 3!")
                } else if (grid[x][y] != '_')
                    println("This cell is occupied! Choose another one!")
                else {
                    grid[x][y] = turn
                    switchTurn()
                    printGameGrid()
                    return
                }
            } catch (e: Exception) {
                println("You should enter numbers!")
            }
        }
    }

    private fun switchTurn() {
        turn = if (turn == 'X') 'O' else 'X'
    }

    private fun getNumberOfCharacters(char: Char): Int =
        grid[0].count { it == char } + grid[1].count { it == char } + grid[2].count { it == char }

    private fun checkWin(symbol: Char): Boolean {
        repeat(3) {
            //row or column
            if (grid[it][0] == grid[it][1] && grid[it][1] == grid[it][2] && grid[it][0] == symbol ||
                grid[0][it] == grid[1][it] && grid[1][it] == grid[2][it] && grid[0][it] == symbol
            )
                return true
        }
        if ((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] ||
                    grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            && grid[1][1] == symbol
        )
            return true

        return false
    }

    private fun printGameGrid() {
        println("---------")
        repeat(3) { i ->
            print("| ")
            repeat(3) { j ->
                print("${grid[i][j]} ")
            }
            println("|")
        }
        println("---------")
    }

    private fun getState(): Boolean {
        val xStatus = checkWin('X')
        val oStatus = checkWin('O')
        val numberOfEmptyCells = getNumberOfCharacters('_')
        if (xStatus == oStatus && !xStatus && numberOfEmptyCells == 0) println("Draw")
        else if (xStatus) println("X wins")
        else if (oStatus) println("O wins")
        else return true

        return false
    }

    fun startGame() {
        printGameGrid()
        while (getState())
            readAndValidateCoordinates()
    }
}

fun main() {
    XoGame.startGame()
}




