class State {
    private var snakeBody: List<Position> = emptyList()
    private var snakeHead: Position
    private var foodPosition = FOOD_INIT_POSITION
    private var direction = Direction.RIGHT

    init {
        setInitSnakeBody()
        snakeHead = snakeBody.last()
    }

    fun moveSnake() {
        when (direction) {
            Direction.RIGHT -> moveSnakeRight()
            Direction.LEFT -> moveSnakeLeft()
            Direction.UP -> moveSnakeUp()
            Direction.DOWN -> moveSnakeDown()
        }
    }

    fun moveSnakeRight(): List<Position> {
        snakeHead = Position(snakeHead.row, snakeHead.column + 1)
        direction = Direction.RIGHT
        updateBody()
        return snakeBody
    }

    fun moveSnakeLeft(): List<Position> {
        snakeHead = Position(snakeHead.row, snakeHead.column - 1)
        direction = Direction.LEFT
        updateBody()
        return snakeBody
    }

    fun moveSnakeUp(): List<Position> {
        snakeHead = Position(snakeHead.row - 1, snakeHead.column)
        direction = Direction.UP
        updateBody()
        return snakeBody
    }

    fun moveSnakeDown(): List<Position> {
        snakeHead = Position(snakeHead.row + 1, snakeHead.column)
        direction = Direction.DOWN
        updateBody()
        return snakeBody
    }

    private fun updateBody() {
        snakeBody = snakeBody.takeLast(snakeBody.size - 1) + snakeHead
    }

    fun drawSnakeDataGrid(): List<List<GridType>> {
        val grid = refreshedBackground()

        snakeBody.forEach {
            grid[it.row][it.column] = GridType.BODY
        }

        snakeBody.last().let {
            grid[it.row][it.column] = GridType.HEAD
        }

        foodPosition.let {
            grid[it.row][it.column] = GridType.FOOD
        }

        return grid
    }

    private fun setInitSnakeBody() {
        val body = mutableListOf<Position>()
        val initPosition = SNAKE_INIT_POSITION

        body.add(initPosition)
        for (i in 1 until SNAKE_INIT_SIZE) {
            body.add(Position(initPosition.row, initPosition.column + i))
        }

        snakeBody = body
    }
}

enum class Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN
}







