val initialBodyPosition: List<Position> by lazy {
    val initSnake = mutableListOf<Position>()
    val initPosition = SNAKE_INIT_POSITION

    initSnake.add(initPosition)
    for (i in 1 until SNAKE_INIT_SIZE) {
        initSnake.add(Position(initPosition.x, initPosition.y + i))
    }

    initSnake
}





