class State {
    private var snakeBody: List<Position> = emptyList()
    private var snakeHead: Position = Position(0, 0)
    private var foodPosition: Position = FOOD_INIT_POSITION
    private var direction = Direction.RIGHT

    init {
        initState()
    }

    private fun initState() {
        setInitSnakeBody()
        snakeHead = snakeBody.last()
        foodPosition = FOOD_INIT_POSITION
        direction = Direction.RIGHT
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
        if (direction == Direction.LEFT) return moveSnakeLeft()

        snakeHead = Position(snakeHead.row, snakeHead.column + 1)
        direction = Direction.RIGHT
        updateState()
        return snakeBody
    }

    fun moveSnakeLeft(): List<Position> {
        if (direction == Direction.RIGHT) return moveSnakeRight()

        snakeHead = Position(snakeHead.row, snakeHead.column - 1)
        direction = Direction.LEFT
        updateState()
        return snakeBody
    }

    fun moveSnakeUp(): List<Position> {
        if (direction == Direction.DOWN) return moveSnakeDown()

        snakeHead = Position(snakeHead.row - 1, snakeHead.column)
        direction = Direction.UP
        updateState()
        return snakeBody
    }

    fun moveSnakeDown(): List<Position> {
        if (direction == Direction.UP) return moveSnakeUp()

        snakeHead = Position(snakeHead.row + 1, snakeHead.column)
        direction = Direction.DOWN
        updateState()
        return snakeBody
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

    private fun updateState() {
        updateBody()

        if (isSnakeHitBody() || isSnakeHitWall()) {
            initState()
        }

        if (isFoodEaten()) {
            updateFood()
        }
    }

    private fun isSnakeHitWall() =
        snakeHead.row < 0 || snakeHead.row >= NUMBER_OF_GRIDS_PER_SIDE
                || snakeHead.column < 0 || snakeHead.column >= NUMBER_OF_GRIDS_PER_SIDE

    private fun isSnakeHitBody() =
        snakeBody
            .take(snakeBody.size - 1)
            .any { snakeHead.row == it.row && snakeHead.column == it.column }

    private fun updateBody() {
        snakeBody = if (isFoodEaten()) {
            snakeBody + snakeHead
        } else {
            snakeBody.takeLast(snakeBody.size - 1) + snakeHead
        }
    }

    private fun updateFood() {
        var row = (0 until NUMBER_OF_GRIDS_PER_SIDE).random()
        var column = (0 until NUMBER_OF_GRIDS_PER_SIDE).random()

        while (isPositionOccupied(row, column)) {
            row = (0 until NUMBER_OF_GRIDS_PER_SIDE).random()
            column = (0 until NUMBER_OF_GRIDS_PER_SIDE).random()
        }

        foodPosition = Position(row, column)
    }

    private fun isPositionOccupiedByBody(row: Int, column: Int) =
        snakeBody.any { it.row == row && it.column == column }

    private fun isPositionOccupiedByFood(row: Int, column: Int) =
        foodPosition.row == row && foodPosition.column == column

    private fun isPositionOccupied(row: Int, column: Int) =
        isPositionOccupiedByBody(row, column) && isPositionOccupiedByFood(row, column)

    private fun isFoodEaten() = snakeHead == foodPosition

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

fun refreshedBackground(): MutableList<MutableList<GridType>> {
    val mutableGrid = MutableList(NUMBER_OF_GRIDS_PER_SIDE) {
        MutableList(NUMBER_OF_GRIDS_PER_SIDE) { GridType.BACKGROUND }
    }

    for (i in 0 until WINDOW_WIDTH) {
        for (j in 0 until WINDOW_HEIGHT) {
            mutableGrid[i][j] = GridType.BACKGROUND
        }
    }

    return mutableGrid
}

enum class Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN
}







