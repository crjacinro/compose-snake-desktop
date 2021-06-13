import kotlin.math.pow

fun getInitSnakeGridData(): List<GridType> {
    val totalNumberOfGrids = NUMBER_OF_GRIDS_PER_SIDE.toDouble().pow(2).toInt()
    val numbers = (0 until totalNumberOfGrids).toList()

    return numbers.map {
        when {
            it <= SNAKE_INIT_POSITION + SNAKE_INIT_SIZE -> GridType.BODY
            it == FOOD_INIT_POSITION -> GridType.FOOD
            else -> GridType.BACKGROUND
        }
    }
}