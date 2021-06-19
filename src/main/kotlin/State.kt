class State {
    private var snakeBody: MutableList<Position> = mutableListOf()
    private var snakeHead: Position
    private val foodPosition = FOOD_INIT_POSITION

    init {
        setInitSnakeBody()
        snakeHead = snakeBody.last()
    }

    fun moveSnakeRight(): List<Position> {
        snakeHead = Position(snakeHead.row, snakeHead.column + 1)
        return updateBody()
    }

    fun moveSnakeLeft(): List<Position> {
        snakeHead = Position(snakeHead.row, snakeHead.column - 1)
        return updateBody()
    }

    fun moveSnakeUp(): List<Position> {
        snakeHead = Position(snakeHead.row - 1, snakeHead.column)
        return updateBody()
    }

    fun moveSnakeDown(): List<Position> {
        snakeHead = Position(snakeHead.row + 1, snakeHead.column)
        return updateBody()
    }

    private fun updateBody(): List<Position> {
        snakeBody.removeFirst()
        snakeBody.add(snakeHead)

        return snakeBody
    }

    fun getSnakePosition() = snakeBody.toList()
    fun getFoodPosition() = foodPosition

    private fun setInitSnakeBody() {
        snakeBody = mutableListOf()
        val initPosition = SNAKE_INIT_POSITION

        snakeBody.add(initPosition)
        for (i in 1 until SNAKE_INIT_SIZE) {
            snakeBody.add(Position(initPosition.row, initPosition.column + i))
        }
    }
}






