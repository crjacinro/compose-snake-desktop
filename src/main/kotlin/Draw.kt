fun drawSnakeData(snakeBody: List<Position>, foodPosition: Position): List<List<GridType>> {
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