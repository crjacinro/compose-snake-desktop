fun getInitSnakeGridData(): List<List<GridType>> {
    val mutableGrid = MutableList(NUMBER_OF_GRIDS_PER_SIDE) {
        MutableList(NUMBER_OF_GRIDS_PER_SIDE) { GridType.BACKGROUND }
    }

    val rowWidth = NUMBER_OF_GRIDS_PER_SIDE
    val rowHeight = NUMBER_OF_GRIDS_PER_SIDE

    for (i in 0 until rowHeight) {
        for (j in 0 until rowWidth) {
            when {
                isInitialBodyPosition(i, j) -> mutableGrid[i][j] = GridType.BODY
                isInitialFoodPosition(i, j) -> mutableGrid[i][j] = GridType.FOOD
                else -> mutableGrid[i][j] = GridType.BACKGROUND
            }
        }
    }

    return mutableGrid
}

private fun isInitialBodyPosition(x: Int, y: Int) =
    initialBodyPosition.any { it.x == x && it.y == y }


private val initialBodyPosition: List<Position> by lazy {
    val initSnake = mutableListOf<Position>()
    val initPosition = SNAKE_INIT_POSITION

    initSnake.add(initPosition)
    for (i in 1 until SNAKE_INIT_SIZE) {
        initSnake.add(Position(initPosition.x, initPosition.y + i))
    }

    initSnake
}

private fun isInitialFoodPosition(x: Int, y: Int) =
    FOOD_INIT_POSITION.x == x && FOOD_INIT_POSITION.y == y



