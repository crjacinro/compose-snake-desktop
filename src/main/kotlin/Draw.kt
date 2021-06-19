fun drawSnakeData(snakeBody: List<Position>, foodPosition: Position): List<List<GridType>> {
    val grid = refreshedBackground()

    snakeBody.forEach {
        grid[it.x][it.y] = GridType.BODY
    }

    snakeBody.last().let {
        grid[it.x][it.y] = GridType.HEAD
    }

    foodPosition.let {
        grid[it.x][it.y] = GridType.FOOD
    }

    return grid
}